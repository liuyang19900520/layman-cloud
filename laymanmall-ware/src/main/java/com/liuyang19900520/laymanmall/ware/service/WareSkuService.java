package com.liuyang19900520.laymanmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.ware.entity.WareSkuEntity;
import com.liuyang19900520.laymanmall.ware.vo.SkuHasStockVo;
import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 23:42:50
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

