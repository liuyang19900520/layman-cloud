package com.liuyang19900520.laymanmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;
import com.liuyang19900520.laymanmall.product.dao.AttrAttrgroupRelationDao;
import com.liuyang19900520.laymanmall.product.entity.AttrAttrgroupRelationEntity;
import com.liuyang19900520.laymanmall.product.service.AttrAttrgroupRelationService;
import com.liuyang19900520.laymanmall.product.vo.AttrGroupRelationVo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends
  ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements
  AttrAttrgroupRelationService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    IPage<AttrAttrgroupRelationEntity> page = this.page(
      new Query<AttrAttrgroupRelationEntity>().getPage(params),
      new QueryWrapper<AttrAttrgroupRelationEntity>());

    return new PageUtils(page);
  }

  @Override
  public void saveAttrRelations(List<AttrGroupRelationVo> relationVo) {

    List<AttrAttrgroupRelationEntity> relationEntities = relationVo.stream().map(item -> {
      AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
      BeanUtils.copyProperties(item, relationEntity);
      return relationEntity;
    }).collect(Collectors.toList());

    this.saveBatch(relationEntities);
  }

}
