package com.liuyang19900520.laymanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.product.entity.SkuSaleAttrValueEntity;
import com.liuyang19900520.laymanmall.product.vo.SkuItemSaleAttrVo;
import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 18:50:19
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId);
}

