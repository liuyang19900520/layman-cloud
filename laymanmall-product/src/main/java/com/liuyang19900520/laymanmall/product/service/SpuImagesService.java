package com.liuyang19900520.laymanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.product.entity.SpuImagesEntity;
import java.util.List;
import java.util.Map;

/**
 * spu图片
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 18:50:19
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuImage(Long id, List<String> spuSaveVoImages);

}

