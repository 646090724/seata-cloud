package com.leij.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leij.order.feign.ProductFeign;
import com.leij.order.mapper.OrderMapper;
import com.leij.order.model.Order;
import com.leij.order.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    ProductFeign productFeign;

    @Override
    public void addOrder(String name) {
        Order order = new Order();
        order.setNo(UUID.randomUUID().toString());
        order.setUserName(name);
        this.save(order);
        productFeign.addProduct(name);

    }

    @Override
    @GlobalTransactional
    public void addOrder1(String name) {
        Order order = new Order();
        order.setNo(UUID.randomUUID().toString());
        order.setUserName(name);
        this.save(order);
        productFeign.addProduct(name);
        int a = 1/0;
        System.out.println(a);

    }

    @Override
    @Transactional
    public void addOrder2(String name) {
        Order order = new Order();
        order.setNo(UUID.randomUUID().toString());
        order.setUserName(name);
        this.save(order);
        productFeign.addProduct(name);
        int a = 1/0;
        System.out.println(a);

    }

    @Override
    public void add(Order order) {
        order.setCreateTime(new Date());//设置订单创建时间
        order.setStatus(0);//try阶段-预检查
        this.save(order);//保存订单
    }

    @Override
    public boolean addCommit(BusinessActionContext context) {
        Order order = JSON.parseObject(context.getActionContext("order").toString(), Order.class);
        order = this.getById(order.getId());
        if (order != null) {
            order.setStatus(1);//commit阶段-提交事务
            this.saveOrUpdate(order);//修改订单
        }
        System.out.println("--------->xid=" + context.getXid() + " 提交成功!");
        return true;
    }

    @Override
    public boolean addRollBack(BusinessActionContext context) {
        String oder = context.getActionContext("order").toString();
        Order order = JSON.parseObject(oder, Order.class);
        order = this.getById(order.getId());
        if (order != null) {
            this.removeById(order.getId());//删除订单
        }
        System.out.println("--------->xid=" + context.getXid() + " 回滚成功!");
        return true;
    }
}
