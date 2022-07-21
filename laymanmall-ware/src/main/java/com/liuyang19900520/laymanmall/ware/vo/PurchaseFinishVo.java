package com.liuyang19900520.laymanmall.ware.vo;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseFinishVo {
    @NotNull
    private Long id;

    private List<PurchaseFinishItem> items;
}
