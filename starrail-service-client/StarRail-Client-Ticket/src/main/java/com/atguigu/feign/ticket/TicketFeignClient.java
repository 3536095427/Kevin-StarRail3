package com.atguigu.feign.ticket;

import com.atguigu.model.common.Result;
import com.atguigu.model.dto.TicketGenerateDTO;
import com.atguigu.model.pojo.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient("starrail-service-ticket")
public interface TicketFeignClient {

    @PostMapping(value = "/api/ticket/generateTicket")
    Result<List<Ticket>> generateRandomTickets(@RequestBody TicketGenerateDTO dto);

    @PostMapping(value = "/api/ticket/auth/addticket")
    boolean addTicket(@RequestParam Ticket ticket);
}
