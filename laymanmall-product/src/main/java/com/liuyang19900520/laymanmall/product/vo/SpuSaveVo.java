/**
  * Copyright 2020 bejson.com
  */
package com.liuyang19900520.laymanmall.product.vo;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * Auto-generated: 2020-05-08 14:3:53
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private Long publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;


}
