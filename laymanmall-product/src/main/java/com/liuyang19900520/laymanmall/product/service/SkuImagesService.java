package com.liuyang19900520.laymanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.product.entity.AttrEntity;
import com.liuyang19900520.laymanmall.product.entity.SkuImagesEntity;

import com.liuyang19900520.laymanmall.product.vo.AttrGroupRelationVo;
import com.liuyang19900520.laymanmall.product.vo.AttrResponseVo;
import com.liuyang19900520.laymanmall.product.vo.AttrVo;
import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

  /**
   * 商品属性
   *
   * @author cosmoswong
   * @email cosmoswong@sina.com
   * @date 2020-04-23 18:50:20
   */
  interface AttrService extends IService<AttrEntity> {

      PageUtils queryPage(Map<String, Object> params);

      void saveAttr(AttrVo attr);

      PageUtils queryBaseAttrPage(Map<String, Object> params, Long cateLogId, String attrType);

      AttrResponseVo getAttrInfo(Long attrId);

      void updateAttr(AttrVo attr);

      List<AttrEntity> getRelationAtr(Long attrgroupId);

      void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

      PageUtils getNoRelationAttr(Long attrgroupId, Map<String, Object> params);

      /**
       * 在指定属性集合中，筛选出支持检索的属性的属性ID
       * @param attrIds
       * @return
       */
      List<Long> selectSearchAttrIds(List<Long> attrIds);
  }
}

