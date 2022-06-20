package com.liuyang19900520.laymanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
public interface CategoryService extends IService<CategoryEntity> {

  List<CategoryEntity> listWithTree();

  PageUtils queryPage(Map<String, Object> params);
}

