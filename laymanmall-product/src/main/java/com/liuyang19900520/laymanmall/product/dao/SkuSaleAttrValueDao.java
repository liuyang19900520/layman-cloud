package com.liuyang19900520.laymanmall.product.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyang19900520.laymanmall.product.entity.SkuSaleAttrValueEntity;
import com.liuyang19900520.laymanmall.product.vo.SkuItemSaleAttrVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * sku销售属性&值
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 18:50:19
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(@Param("spuId") Long spuId);
}
