package com.leij.business.service.impl;

import com.leij.business.feign.OrderServiceFeign;
import com.leij.business.feign.PointsServiceFeign;
import com.leij.business.feign.ProductFeign;
import com.leij.business.feign.StorageServiceFeign;
import com.leij.business.model.Order;
import com.leij.business.service.BusinessService;
import com.leij.business.utils.IdWorker;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    OrderServiceFeign orderServiceFeign;

    @Autowired
    PointsServiceFeign pointsServiceFeign;

    @Autowired
    ProductFeign storageServiceFeign;

    @Autowired
    IdWorker idWorker;

    /**
     * 商品销售
     *
     * @param goodsId  商品id
     * @param num      销售数量
     * @param username 用户名
     * @param money    金额
     */
    //@Transactional
    @GlobalTransactional(name = "sale", timeoutMills = 100000, rollbackFor = Exception.class)
    public void sale(Integer goodsId, Integer num, Double money, String username) {
        //创建订单
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setGoodsId(goodsId);
        order.setNum(num);
        order.setMoney(money);
        order.setUsername(username);
        orderServiceFeign.addOrder(order);
        //增加积分
       // pointsServiceFeign.increase(username, (int) (money / 10));
        //扣减库存
        storageServiceFeign.decrease(goodsId, num);
    }
}
