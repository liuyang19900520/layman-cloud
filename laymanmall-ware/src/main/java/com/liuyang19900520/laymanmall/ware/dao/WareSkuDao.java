package com.liuyang19900520.laymanmall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyang19900520.laymanmall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 23:42:50
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

  void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId,
    @Param("skuNum") Integer skuNum);

  Long getSkuStock(@Param("item") Long item);
}
