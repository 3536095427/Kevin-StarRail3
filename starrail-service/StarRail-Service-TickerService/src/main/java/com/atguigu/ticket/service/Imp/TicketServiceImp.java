package com.atguigu.ticket.service.Imp;


import cn.hutool.core.util.RandomUtil;
import com.atguigu.ticket.Utils.TicketUtils;
import com.atguigu.ticket.dao.TicketMapper;
import com.atguigu.feign.travelpath.TravelPathClient;
import com.atguigu.model.dto.TicketGenerateDTO;
import com.atguigu.model.pojo.Ticket;
import com.atguigu.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TravelPathClient travelPathClient;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Ticket getTicketById(String ticketId) {
        return ticketMapper.getTicketById(ticketId);
    }

    @Cacheable(value = "randomTickets",key = "#dto.pathSelectDTO.startStation + #dto.pathSelectDTO.destination + #dto.pathSelectDTO.startDate")
    @Override
    public List<Ticket> generateRandomTickets(TicketGenerateDTO dto){
        return this.updateRandomTickets(dto);
    }

    @CachePut(value = "randomTickets",key = "#dto.pathSelectDTO.startStation + #dto.pathSelectDTO.destination + #dto.pathSelectDTO.startDate")
    public List<Ticket> updateRandomTickets(TicketGenerateDTO dto){
        List<Ticket> ticketFactors = travelPathClient.getTicketFactor(dto.getPathSelectDTO()).getData();
        TicketUtils ticketUtils = new TicketUtils();
        return ticketUtils.getRandomTicketsByFactor(ticketFactors, dto.getGenerateNum());
    }

    @Override
    public int addTicket(Ticket ticket) {
        // 重新生成车票Id，防止无法二次购买同一车次的的车票
        ticket.setTicketId(RandomUtil.randomNumbers(8));
        return ticketMapper.addTicket(ticket);
    }
}
