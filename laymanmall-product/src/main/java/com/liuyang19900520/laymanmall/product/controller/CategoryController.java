package com.liuyang19900520.laymanmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;
import com.liuyang19900520.laymanmall.product.service.CategoryService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.R;


/**
 * 商品三级分类
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 列表
   */
  @RequestMapping("/list/tree")
  public R list(@RequestParam Map<String, Object> params) {
    List<CategoryEntity> categoryEntities = categoryService.listWithTree();
    //找到所有的一级分类
    List<CategoryEntity> level1Menus = categoryEntities.stream()
      .filter(item -> item.getParentCid() == 0).map(menu -> {
        menu.setChildren(getChildrens(menu, categoryEntities));
        return menu;
      }).sorted((menu1, menu2) -> {

        return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0
          : menu2.getSort());

      }).collect(Collectors.toList());
    return R.ok().put("data", level1Menus);
  }


  /**
   * 信息
   */
  @RequestMapping("/info/{catId}")
  public R info(@PathVariable("catId") Long catId) {
    CategoryEntity category = categoryService.getById(catId);

    return R.ok().put("data", category);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  public R save(@RequestBody CategoryEntity category) {
    categoryService.save(category);

    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update")
  public R update(@RequestBody CategoryEntity category) {
    categoryService.updateCascade(category);

    return R.ok();
  }

  @RequestMapping("/update/sort")
  public R updateSort(@RequestBody CategoryEntity[] category){
    categoryService.updateBatchById(Arrays.asList(category));

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] catIds) {
    categoryService.removeByIds(Arrays.asList(catIds));

    return R.ok();
  }

  public List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {

    List<CategoryEntity> childrens = all.stream().filter(item -> {
      //请注意这里的两个数据的比较，它们都是Long型的，最好使用equals方法
      return item.getParentCid().equals(root.getCatId());
    }).map(item -> {
      item.setChildren(getChildrens(item, all));
      return item;
    }).sorted((menu1, menu2) -> {
      return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0
        : menu2.getSort());
    }).collect(Collectors.toList());

    return childrens;
  }

}
