package com.liuyang19900520.laymanmall.product.feign;


import com.liuyang19900520.laymanmall.common.to.SkuReductionTo;
import com.liuyang19900520.laymanmall.common.to.SpuBoundTo;
import com.liuyang19900520.laymanmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "layman-coupon")
public interface CouponFenService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);


}
