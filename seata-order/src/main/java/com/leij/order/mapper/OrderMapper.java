package com.leij.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leij.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
