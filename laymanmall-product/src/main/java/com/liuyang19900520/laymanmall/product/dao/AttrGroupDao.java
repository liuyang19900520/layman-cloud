package com.liuyang19900520.laymanmall.product.dao;

import com.liuyang19900520.laymanmall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyang19900520.laymanmall.product.vo.SpuItemAttrGroupVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 属性分组
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 16:52:22
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {
  List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);

}
