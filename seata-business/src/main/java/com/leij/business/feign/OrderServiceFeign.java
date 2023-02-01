package com.leij.business.feign;

import com.leij.business.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("seata-order")
public interface OrderServiceFeign {


    @PostMapping("/order/addTcc")
    public void addOrder(@RequestBody Order order);
}
