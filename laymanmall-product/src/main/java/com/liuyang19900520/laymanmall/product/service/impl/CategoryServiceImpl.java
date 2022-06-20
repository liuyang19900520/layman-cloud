package com.liuyang19900520.laymanmall.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;

import com.liuyang19900520.laymanmall.product.dao.CategoryDao;
import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;
import com.liuyang19900520.laymanmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements
  CategoryService {

  @Autowired
  CategoryDao categoryDao;

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<CategoryEntity> page = this.page(new Query<CategoryEntity>().getPage(params),
      new QueryWrapper<CategoryEntity>());

    return new PageUtils(page);
  }

  @Override
  public List<CategoryEntity> listWithTree() {
    List<CategoryEntity> categoryEntities = categoryDao.selectList(null);
    return categoryEntities;
  }
}
