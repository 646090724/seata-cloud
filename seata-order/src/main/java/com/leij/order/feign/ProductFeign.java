package com.leij.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seata-product")
public interface ProductFeign {

    @GetMapping("/product/add")
    public void addProduct(@RequestParam("name") String name);
}
