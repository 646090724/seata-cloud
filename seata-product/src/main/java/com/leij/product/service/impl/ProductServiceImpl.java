package com.leij.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leij.product.mapper.ProductMapper;
import com.leij.product.model.Product;
import com.leij.product.service.ProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    /**
     * 减少库存
     *
     * @param goodsId  商品ID
     * @param quantity 减少数量
     * @return 库存对象
     */
    public void decrease(Integer goodsId, Integer quantity) {
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        wrapper.lambda().eq(Product::getGoodsId, goodsId);
        Product goodsStorage = this.getOne(wrapper);
        if (goodsStorage.getStorage() >= quantity) {
            //设置冻结库存
            goodsStorage.setFrozenStorage(quantity);
        } else {
            throw new RuntimeException(goodsId + "库存不⾜,⽬前剩余库存:"
                    + goodsStorage.getStorage());
        }
        this.saveOrUpdate(goodsStorage);
    }

    @Override
    public boolean decreaseCommit(BusinessActionContext context) {
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        wrapper.lambda().eq(Product::getGoodsId,
                context.getActionContext("goodsId"));
        Product goodsStorage = this.getOne(wrapper);
        if (goodsStorage != null) {
            //扣减库存
            goodsStorage.setStorage(goodsStorage.getStorage() -
                    goodsStorage.getFrozenStorage());
            //冻结库存清零
            goodsStorage.setFrozenStorage(0);
            this.saveOrUpdate(goodsStorage);
        }
        System.out.println("--------->xid=" + context.getXid() + " 提交成功!");
        return true;
    }

    @Override
    public boolean decreaseRollback(BusinessActionContext context) {
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        wrapper.lambda().eq(Product::getGoodsId,
                context.getActionContext("goodsId"));
        Product goodsStorage = this.getOne(wrapper);
        if (goodsStorage != null) {
            //冻结库存清零
            goodsStorage.setFrozenStorage(0);
            this.saveOrUpdate(goodsStorage);
        }
        System.out.println("--------->xid=" + context.getXid() + " 回滚成功!");
        return true;
    }
}
