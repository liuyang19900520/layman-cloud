package com.liuyang19900520.laymanmall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyang19900520.laymanmall.common.to.SkuHasStockVo;
import com.liuyang19900520.laymanmall.common.to.SkuReductionTo;
import com.liuyang19900520.laymanmall.common.to.SpuBoundTo;
import com.liuyang19900520.laymanmall.common.to.es.SkuEsModel;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;
import com.liuyang19900520.laymanmall.common.utils.R;
import com.liuyang19900520.laymanmall.product.dao.SpuInfoDao;
import com.liuyang19900520.laymanmall.product.entity.BrandEntity;
import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;
import com.liuyang19900520.laymanmall.product.entity.ProductAttrValueEntity;
import com.liuyang19900520.laymanmall.product.entity.SkuImagesEntity;
import com.liuyang19900520.laymanmall.product.entity.SkuInfoEntity;
import com.liuyang19900520.laymanmall.product.entity.SkuSaleAttrValueEntity;
import com.liuyang19900520.laymanmall.product.entity.SpuInfoDescEntity;
import com.liuyang19900520.laymanmall.product.entity.SpuInfoEntity;
import com.liuyang19900520.laymanmall.product.feign.CouponFenService;
import com.liuyang19900520.laymanmall.product.feign.WareFeignService;
import com.liuyang19900520.laymanmall.product.service.AttrService;
import com.liuyang19900520.laymanmall.product.service.BrandService;
import com.liuyang19900520.laymanmall.product.service.CategoryService;
import com.liuyang19900520.laymanmall.product.service.ProductAttrValueService;
import com.liuyang19900520.laymanmall.product.service.SkuImagesService;
import com.liuyang19900520.laymanmall.product.service.SkuInfoService;
import com.liuyang19900520.laymanmall.product.service.SkuSaleAttrValueService;
import com.liuyang19900520.laymanmall.product.service.SpuImagesService;
import com.liuyang19900520.laymanmall.product.service.SpuInfoDescService;
import com.liuyang19900520.laymanmall.product.service.SpuInfoService;
import com.liuyang19900520.laymanmall.product.vo.Attr;
import com.liuyang19900520.laymanmall.product.vo.BaseAttrs;
import com.liuyang19900520.laymanmall.product.vo.Bounds;
import com.liuyang19900520.laymanmall.product.vo.Images;
import com.liuyang19900520.laymanmall.product.vo.Skus;
import com.liuyang19900520.laymanmall.product.vo.SpuSaveVo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
@Slf4j
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements
  SpuInfoService {

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    ProductAttrValueService attrValueService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    CouponFenService couponFenService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    //@Autowired
    private WareFeignService wareFeignService;

    //@Autowired
    //SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        //1. ??????spu???????????????pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo,spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());

        this.saveBaseSpuInfo(spuInfoEntity);
        //2. ??????SPU??????????????????pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();

        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuInfoEntity.getId());
        descEntity.setDecript(String.join(",",decript));

        spuInfoDescService.saveSupInfoDesc(descEntity);


        //3. ??????SPU???????????????pms_spu_images
        List<String> spuSaveVoImages = spuSaveVo.getImages();

        spuImagesService.saveSpuImage(spuInfoEntity.getId(),spuSaveVoImages);

        //4. ??????SPU??????????????????pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(item -> {
            ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
            attrValueEntity.setSpuId(spuInfoEntity.getId());
            attrValueEntity.setAttrId(item.getAttrId());
            attrValueEntity.setAttrName(attrService.getById(item.getAttrId()).getAttrName());
            attrValueEntity.setAttrValue(item.getAttrValues());
            attrValueEntity.setQuickShow(item.getShowDesc());

            return attrValueEntity;
        }).collect(Collectors.toList());

        productAttrValueService.saveProductAttrValueEntities(productAttrValueEntities);


        //5.0 ??????SPU??????????????????sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());

        R r = couponFenService.saveSpuBounds(spuBoundTo);
        if(r.getCode() != 0){
            log.error("????????????SPU??????????????????");
        }

        //5. ??????SPU???????????????SKU??????
        List<Skus> skus = spuSaveVo.getSkus();

        if(skus != null && skus.size() > 0){


            skus.forEach(item -> {
                //?????????SKU?????????images????????????????????????????????????????????????????????????default_img=1
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if(image.getDefaultImg() == 1){
                        defaultImg = image.getImgUrl();
                    }
                }

                //5.1 SKU??????????????????pms_sku_info
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item,skuInfoEntity);
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                //skuInfoEntity.setSkuDesc();
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoEntity.setSaleCount(0L);

                skuInfoService.saveSkuInfo(skuInfoEntity);

                //5.2 SKU??????????????????pms_spu_images
                List<Images> images = item.getImages();
                List<SkuImagesEntity> skuImagesEntities = images.stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    BeanUtils.copyProperties(img, skuImagesEntity);
                    skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());

                    return skuImagesEntity;
                }).filter(entity -> {
                    return StringUtils.isNotEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());

                skuImagesService.saveBatch(skuImagesEntities);


                //5.3 SKU????????????????????????pms_sku_sale_attr_value
                List<Attr> attrs = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attrs.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());

                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                //5.4 SKU??????????????????????????????sms_sku_ladder???sms_sku_full_reduction???sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item,skuReductionTo);
                skuReductionTo.setSkuId(skuInfoEntity.getSkuId());

                if(skuReductionTo.getFullCount() <=0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
                    R r1 = couponFenService.saveSkuReduction(skuReductionTo);
                    if(r1.getCode() != 0){
                        log.error("????????????SKU??????????????????");
                    }
                }



            });

        }

    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();

        String key=(String)params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.and(item -> {
               item.eq("id",key).or().like("spu_name",key);
            });
        }

        String status=(String)params.get("status");
        if(StringUtils.isNotEmpty(status)){
            queryWrapper.eq("publish_status",status);
        }

        String brandId=(String)params.get("brandId");
        if(StringUtils.isNotEmpty(brandId) && (!"0".equalsIgnoreCase(brandId))){
            queryWrapper.eq("brand_id",brandId);
        }

        String catelogId=(String) params.get("catelogId");
        if(StringUtils.isNotEmpty(catelogId) && (!"0".equalsIgnoreCase(catelogId))){
            queryWrapper.eq("catalog_id",catelogId);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * ???????????????????????????????????????SkuEsModel?????????????????????ES???????????????
     * ?????????????????????
     * ???1???Attrs???????????????????????????????????????
     * ???2???????????????????????????????????????gulimall-ware??????????????????openfeign????????????????????????
     * ???3???????????????????????????ES?????????????????????????????????????????????????????????????????????????????????
     * ??????ES????????????????????????ID????????????????????????????????????
     * @param spuId
     */
    @Override
    public void up(Long spuId) {

        //?????????supId????????????SKU????????????????????????
        List<SkuInfoEntity> skuInfoEntities=skuInfoService.getSkusBySpuId(spuId);

        //TODO 4. ????????????SKU?????????????????????????????????????????????
        List<ProductAttrValueEntity> productAttrValueEntities = attrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIds = productAttrValueEntities.stream().map(item -> {
            Long attrId = item.getAttrId();
            return attrId;
        }).collect(Collectors.toList());

        //???????????????????????????????????????ID
        List<Long> searchAttrIds=attrService.selectSearchAttrIds(attrIds);
        HashSet<Long> searchAttrIdsSet = new HashSet<>(searchAttrIds);
        //????????????????????????Attrs???????????????SkuEsModel???attrs??????
        List<SkuEsModel.Attrs> attrsList = productAttrValueEntities.stream().filter(item -> {
            return searchAttrIdsSet.contains(item.getAttrId());
        }).map(item -> {
            SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
            attrs.setAttrId(item.getAttrId());
            attrs.setAttrName(item.getAttrName());
            attrs.setAttrValue(item.getAttrValue());
            return attrs;
        }).collect(Collectors.toList());

        //??????sku????????????????????????????????????????????????????????????SkuEsModel???HasStock????????????
        Map<Long, Boolean> stockMap = null;
        try {
            List<Long> skuInfoSkuIds = skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
            R skuHasStock = wareFeignService.getSkuHasStock(skuInfoSkuIds);

            stockMap = skuHasStock.getData(new TypeReference<List<SkuHasStockVo>>(){}).stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } catch (Exception e) {
            log.error("?????????????????????????????????{}",e);
        }

        //????????????SKU?????????
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> collect = skuInfoEntities.stream().map(item -> {
            //?????????????????????
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(item, skuEsModel);
            //skuImg skuPrice
            skuEsModel.setSkuPrice(item.getPrice());
            skuEsModel.setSkuImg(item.getSkuDefaultImg());

            //hasStock
            //TODo 1.??????????????????????????????????????????????????????
            if(finalStockMap == null){
                skuEsModel.setHasStock(true);
            }else{
                skuEsModel.setHasStock(finalStockMap.get(item.getSkuId()));
            }

            //hotScore
            //TODO 2. ?????????????????????0
            skuEsModel.setHotScore(0L);

            //brandImg brandName   catelogName
            //TODO 3. ????????????????????????????????????
            BrandEntity brandEntity = brandService.getById(skuEsModel.getBrandId());
            skuEsModel.setBrandName(brandEntity.getName());
            skuEsModel.setBrandImg(brandEntity.getLogo());

             //catalog???catelog?????????????????????
            skuEsModel.setCatelogId(item.getCatalogId());
            CategoryEntity categoryEntity = categoryService.getById(skuEsModel.getCatelogId());
            skuEsModel.setCatelogName(categoryEntity.getName());

            //?????????????????????attrs
            skuEsModel.setAttrs(attrsList);


            return skuEsModel;

        }).collect(Collectors.toList());

//         //TODO 5. ??????????????????ES???????????????gulimall-search
//        R statusUp = searchFeignService.productStatusUp(collect);
//        if(statusUp.getCode() == 0){
//             //??????????????????
//            //TODO 6.???????????????SPU??????
//            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
//        }else {
//            //??????????????????
//            //TODO 7.???????????????????????????????????????
//
//
//        }

    }


    private void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

}
