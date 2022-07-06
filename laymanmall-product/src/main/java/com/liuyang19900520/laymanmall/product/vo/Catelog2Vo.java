package com.liuyang19900520.laymanmall.product.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * 二级分类VO
 */
public class Catelog2Vo {

    private String catalog1Id;
    private List<Catalog3List> catalog3List;
    private String id;
    private String name;
}
