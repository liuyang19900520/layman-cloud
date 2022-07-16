package com.liuyang19900520.laymanmall.product.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;
import com.liuyang19900520.laymanmall.product.dao.SkuSaleAttrValueDao;
import com.liuyang19900520.laymanmall.product.entity.SkuSaleAttrValueEntity;
import com.liuyang19900520.laymanmall.product.service.SkuSaleAttrValueService;
import com.liuyang19900520.laymanmall.product.vo.SkuItemSaleAttrVo;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends
  ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements
    SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId) {

        return this.baseMapper.getSaleAttrsBySpuId(spuId);
    }

}
