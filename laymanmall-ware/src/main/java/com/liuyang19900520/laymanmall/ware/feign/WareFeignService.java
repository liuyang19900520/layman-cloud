package com.liuyang19900520.laymanmall.ware.feign;

import com.liuyang19900520.laymanmall.common.utils.R;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("layman-ware")
public interface WareFeignService {

  @PostMapping("/ware/waresku/hasStock")
  R getSkuHasStock(@RequestBody List<Long> skuIds);

}
