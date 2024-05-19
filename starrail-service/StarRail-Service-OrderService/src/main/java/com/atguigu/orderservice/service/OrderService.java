package com.atguigu.orderservice.service;


import com.atguigu.model.dto.OrderDTO;
import com.atguigu.model.pojo.Order;

import java.util.List;

public interface OrderService {

    int addOrder(Order order);

    List<Order> getOrderByOwner(String token);

    Order creatOrder(OrderDTO dto);

    Boolean deleteOrderByOrderId(String orderId,String token);

    Boolean payOrder(String orderId,String token);

    Order getSelectedOrderInfo(String orderId,String token);
}
