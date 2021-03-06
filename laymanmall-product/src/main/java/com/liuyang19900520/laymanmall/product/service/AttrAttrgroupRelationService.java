package com.liuyang19900520.laymanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.product.entity.AttrAttrgroupRelationEntity;
import com.liuyang19900520.laymanmall.product.vo.AttrGroupRelationVo;
import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 18:50:19
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrRelations(List<AttrGroupRelationVo> relationVo);
}

