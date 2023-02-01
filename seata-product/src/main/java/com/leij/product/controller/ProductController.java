package com.leij.product.controller;

import com.leij.product.model.Product;
import com.leij.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/product/decrease")
    public void decrease(Integer goodsId, Integer quantity) {
        service.decrease(goodsId, quantity);
    }

    @GetMapping("/product/add")
    public void addProduct(@RequestParam String name) {
        Product product = new Product();
        product.setUserName(name);
        product.setNo(UUID.randomUUID().toString());
        service.save(product);

    }

}
