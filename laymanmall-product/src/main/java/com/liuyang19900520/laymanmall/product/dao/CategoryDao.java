package com.liuyang19900520.laymanmall.product.dao;

import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
