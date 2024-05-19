package com.atguigu.ticket.controller;


import com.atguigu.model.common.Result;
import com.atguigu.model.common.ResultCodeEnum;
import com.atguigu.model.dto.TicketGenerateDTO;
import com.atguigu.model.pojo.Ticket;
import com.atguigu.ticket.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Tag(name = "车票管理")
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @PostMapping("/generateTicket")
    Result<List<Ticket>> generateRandomTickets(@RequestBody TicketGenerateDTO ticketGenerateDTO){
        return Result.build(ticketService.generateRandomTickets(ticketGenerateDTO), ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/auth/addticket")
    Result<Boolean> addTicket(Ticket ticket){
        ticketService.addTicket(ticket);
        return Result.build(true, ResultCodeEnum.SUCCESS);
    }
}
