package com.liuyang19900520.laymanmall.search.vo;

import java.util.List;
import lombok.Data;

/**
 * 查询的属性信息
 */
@Data
public class AttrVo {
    private Long attrId;

    private String attrName;

    private List<String> attrValue;
}
