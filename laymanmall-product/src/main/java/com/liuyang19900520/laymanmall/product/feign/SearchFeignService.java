package com.liuyang19900520.laymanmall.product.feign;


import com.liuyang19900520.laymanmall.common.to.es.SkuEsModel;
import com.liuyang19900520.laymanmall.common.utils.R;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("layman-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
