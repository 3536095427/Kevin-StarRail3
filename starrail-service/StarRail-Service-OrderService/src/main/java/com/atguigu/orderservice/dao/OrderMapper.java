package com.atguigu.orderservice.dao;


import com.atguigu.model.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface OrderMapper {

    int addOrder(Order order);


    @Cacheable(value = "orderlist",key = "#token")
    List<Order> getOrderByOwnerId(@Param("ownerId") int ownerId,String token);

    @CacheEvict(value = "order", key = "#token")
    @Delete("delete from `order` where order_id = #{orderId};")
    int deleteOrderByOrderId(@Param("orderId")String orderId,String token);

    @CacheEvict(value = "order", key = "#token")
    @Update("update `order` set payment_status = 1 where order_id = #{orderId};")
    int payOrder(@Param("orderId")String orderId,String token);
}
