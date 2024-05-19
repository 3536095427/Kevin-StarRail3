package com.atguigu.travel.controller;


import com.atguigu.feign.ticket.TicketFeignClient;
import com.atguigu.model.common.Result;
import com.atguigu.model.common.ResultCodeEnum;
import com.atguigu.model.dto.PathSelectDTO;
import com.atguigu.model.dto.TicketGenerateDTO;
import com.atguigu.model.pojo.Ticket;
import com.atguigu.travel.service.StationService;
import com.atguigu.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Tag(name = "路线规划接口")
@RestController
@RequestMapping("/api/travelpath")
public class TravelPathController {

    @Autowired
    private StationService stationService;

    @Autowired
    private TravelService travelService;

    @Resource
    private TicketFeignClient ticketService;


    @Operation(summary = "返回查询页面")
    @GetMapping("/consult")
    public String returnFormOrders(){
        return "redirect:/api/travelpath/consult";
    }


    @Operation(summary = "查询路线信息")
    @PostMapping("/consult")
    public Result<List<Ticket>> consult(@RequestBody PathSelectDTO pathSelectDTO) {

        // 车票的数量范围 5 - 10
        Random random = new Random();
        int ticketsNum = random.nextInt(3) + 5;
        List<Ticket> tickets = ticketService.generateRandomTickets(new TicketGenerateDTO(pathSelectDTO,ticketsNum)).getData();
        return Result.build(tickets, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取车票因子")
    @PostMapping("/getTicketFactor")
    Result<List<Ticket>> getTicketFactor(@RequestBody PathSelectDTO dto){
        List<Ticket> ticketFactor = travelService.getTicketFactor(dto);
        return Result.build(ticketFactor, ResultCodeEnum.SUCCESS);
    }
}
