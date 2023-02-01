package com.leij.order.controller;

import com.leij.order.model.Order;
import com.leij.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    private OrderService service;


    /**
     * 下单
     */
    @PostMapping("/order/addTcc")
    public void addOrder(@RequestBody Order order) {
        service.add(order);
    }


    /**
     * 正常添加
     *
     * @param name
     * @return
     */
    @GetMapping("/order/add")
    public String addOrder(@RequestParam(name = "name") String name) {
        service.addOrder(name);
        return "添加成功";
    }

    /**
     * 使用分布式事务添加报错
     *
     * @param name
     * @return
     */
    @GetMapping("/order/add1")
    public String addOrder1(@RequestParam(name = "name") String name) {
        service.addOrder1(name);
        return "添加成功";
    }


    /**
     * 不使用分布式事务添加报错
     *
     * @param name
     * @return
     */
    @GetMapping("/order/add2")
    public String addOrder2(@RequestParam(name = "name") String name) {
        service.addOrder2(name);
        return "添加成功";
    }
}
