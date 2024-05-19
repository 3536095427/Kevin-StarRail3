package com.atguigu.ticket.dao;


import com.atguigu.model.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    Ticket getTicketById(String id);

    int addTicket(Ticket ticket);
}
