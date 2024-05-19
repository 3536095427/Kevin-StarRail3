package com.atguigu.orderservice.controller;


import com.atguigu.feign.ticket.TicketFeignClient;
import com.atguigu.model.common.Result;
import com.atguigu.model.common.ResultCodeEnum;
import com.atguigu.model.dto.OrderDTO;
import com.atguigu.model.pojo.Order;
import com.atguigu.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "订单管理")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketFeignClient ticketFeignClient;


    @Operation(summary = "创建订单")
    @PostMapping("/auth/order")
    public Result<Boolean> createOrder(OrderDTO orderDTO){
        Order order = orderService.creatOrder(orderDTO);
        return Result.build(true, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户订单列表")
    @GetMapping("/auth/orders")
    public Result<List<Order>> myOrder(HttpServletRequest request){
        List<Order> orderList = orderService.getOrderByOwner(request.getHeader("token"));
        return Result.build(orderList, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "获取指定订单的详细信息")
    @GetMapping("/auth/order")
    public Result<Order> getOrderInfo(@RequestParam("orderId") String orderId,
                                     HttpServletRequest request){
        Order result = orderService.getSelectedOrderInfo(orderId,request.getHeader("token"));
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }



    @Operation(summary = "删除已支付订单")
    @DeleteMapping("/auth/order")
    public Result<Boolean> deleteOrder(@RequestParam("orderId") String orderId,
                                    HttpServletRequest request){
        boolean result = orderService.deleteOrderByOrderId(orderId,request.getHeader("token"));
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "继续支付订单")
    @PutMapping ("/auth/payOrder")
    public Result<Boolean> payOrder(@RequestParam("orderId")String orderId,
                                 HttpServletRequest request){
        Boolean result = orderService.payOrder(orderId,request.getHeader("token"));
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }
}
