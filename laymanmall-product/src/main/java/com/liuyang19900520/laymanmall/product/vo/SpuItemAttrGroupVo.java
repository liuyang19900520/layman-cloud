package com.liuyang19900520.laymanmall.product.vo;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
