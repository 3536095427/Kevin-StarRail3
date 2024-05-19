package com.atguigu.orderservice.service.Imp;


import cn.hutool.core.util.RandomUtil;
import com.atguigu.commonservice.utils.AuthContext;
import com.atguigu.feign.ticket.TicketFeignClient;
import com.atguigu.model.dto.OrderDTO;
import com.atguigu.model.pojo.Order;
import com.atguigu.model.pojo.UserBasic;
import com.atguigu.orderservice.dao.OrderMapper;
import com.atguigu.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Resource
    private TicketFeignClient ticketFeignClient;

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public List<Order> getOrderByOwner(String token) {
        UserBasic userBasic = AuthContext.getAuthContext();
        return orderMapper.getOrderByOwnerId(userBasic.getId(),token);
    }

    @Override
    @Transactional
    public Order creatOrder(OrderDTO orderDTO) {
        UserBasic owner = AuthContext.getAuthContext();

        // 随机订单ID
        String orderId = RandomUtil.randomNumbers(10);
        // 获取订单生成时间
        LocalDateTime now = LocalDateTime.now();

        Order order =  new Order(orderId, owner, orderDTO.getPassengerName(),
                orderDTO.getPassengerId(), now, 0, orderDTO.getTicket());

        // 将订单存入数据库
        addOrder(order);
        // 将车票存入数据库
        ticketFeignClient.addTicket(order.getTicket());

        return order;
    }

    @Override
    public Boolean deleteOrderByOrderId(String orderId,String token) {
        return orderMapper.deleteOrderByOrderId(orderId,token) != 0;
    }

    @Override
    public Boolean payOrder(String orderId,String token) {
        int res = orderMapper.payOrder(orderId,token);
        return res != 0;
    }

    @Override
    public Order getSelectedOrderInfo(String orderId, String token) {
        List<Order> orderList = getOrderByOwner(token);

        return orderList.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst().orElse(null);
    }
}
