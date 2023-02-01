package com.leij.product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("seata_product")
public class Product {

    @TableId(type = IdType.AUTO)
    private int id;
    @TableField("no")
    private String no;
    @TableField("user_name")
    private String userName;
    @TableField
    private String goodsId;//商品ID
    @TableField
    private Integer storage;//库存量
    @TableField
    private Integer frozenStorage; //冻结库存

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getFrozenStorage() {
        return frozenStorage;
    }

    public void setFrozenStorage(Integer frozenStorage) {
        this.frozenStorage = frozenStorage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
