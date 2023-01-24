package com.liuyang19900520.laymanmall.product.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyang19900520.laymanmall.product.entity.BrandEntity;
import com.liuyang19900520.laymanmall.product.vo.BrandVo;
import java.util.Arrays;
import java.util.List;


import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang19900520.laymanmall.product.entity.CategoryBrandRelationEntity;
import com.liuyang19900520.laymanmall.product.service.CategoryBrandRelationService;
import com.liuyang19900520.laymanmall.common.utils.R;


/**
 * 品牌分类关联
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

  @Autowired
  private CategoryBrandRelationService categoryBrandRelationService;

  /**
   * 列表
   */
  @GetMapping("/brands/list")
  public R relationBrandList(@RequestParam(value = "catId", required = true) Long catId) {
    List<BrandEntity> vos = categoryBrandRelationService.getBrandById(catId);
    List<BrandVo> brandVos = vos.stream().map(item -> {
      BrandVo brandVo = new BrandVo();
      brandVo.setBrandId(item.getBrandId());
      brandVo.setBrandName(item.getName());
      return brandVo;
    }).collect(Collectors.toList());
    return R.ok().put("data", brandVos);
  }

  @GetMapping("/catelog/list")
  public R cateLoglist(@RequestParam("brandId") Long brandId) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("brand_id", brandId);
    List list = categoryBrandRelationService.list(queryWrapper);

    return R.ok().put("data", list);
  }

  /**
   * 信息
   */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

    return R.ok().put("categoryBrandRelation", categoryBrandRelation);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.saveDetails(categoryBrandRelation);

    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update")
  public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.updateById(categoryBrandRelation);

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    categoryBrandRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

}
