package com.liuyang19900520.laymanmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;
import com.liuyang19900520.laymanmall.product.dao.AttrAttrgroupRelationDao;
import com.liuyang19900520.laymanmall.product.dao.AttrGroupDao;
import com.liuyang19900520.laymanmall.product.entity.AttrEntity;
import com.liuyang19900520.laymanmall.product.entity.AttrGroupEntity;
import com.liuyang19900520.laymanmall.product.service.AttrGroupService;
import com.liuyang19900520.laymanmall.product.service.AttrService;
import com.liuyang19900520.laymanmall.product.service.SkuImagesService;
import com.liuyang19900520.laymanmall.product.vo.AttrGroupWithAttrVo;
import com.liuyang19900520.laymanmall.product.vo.SpuItemAttrGroupVo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements
  AttrGroupService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long cateLogId) {

            String queryKey = (String) params.get("key");

            QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
            //catelog_id == 0????????????attr_group_id???attr_group_name????????????????????????????????????catelog_id
            if(cateLogId != 0){
                queryWrapper.eq("catelog_id", cateLogId);
            }
            //select * from pms_attr_group WHERE catelog_id = 1 AND (attr_group_id =key or attr_group_name LIKE '%key%');
            if (StringUtils.isNotEmpty(queryKey)) {

                queryWrapper.and((param) -> {
                    param.eq("attr_group_id", queryKey)
                            .or()
                            .like("attr_group_name", queryKey);
                });
            }

            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    queryWrapper
            );

            return new PageUtils(page);
    }

    @Override
    public List<AttrGroupWithAttrVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //?????????????????????????????????????????????
        List<AttrGroupEntity> attrGroupEntities = baseMapper.selectList(new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catelogId));
        //????????????????????????????????????????????????
        List<AttrGroupWithAttrVo> attrGroupWithAttrVos = attrGroupEntities.stream().map(item -> {
            AttrGroupWithAttrVo attrGroupWithAttrVo = new AttrGroupWithAttrVo();
            BeanUtils.copyProperties(item, attrGroupWithAttrVo);
            List<AttrEntity> relationAtr = attrService.getRelationAtr(attrGroupWithAttrVo.getAttrGroupId());
            attrGroupWithAttrVo.setAttrs(relationAtr);
            return attrGroupWithAttrVo;
        }).collect(Collectors.toList());


        return attrGroupWithAttrVos;
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        //1.???????????????SPU???????????????????????????????????????????????????????????????????????????????????????
        return this.baseMapper.getAttrGroupWithAttrsBySpuId(spuId,catalogId);
    }

}
