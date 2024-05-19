package com.atguigu.ticket.service;


import com.atguigu.model.dto.TicketGenerateDTO;
import com.atguigu.model.pojo.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> generateRandomTickets(TicketGenerateDTO ticketGenerateDTO);

    Ticket getTicketById(String ticketId);

    int addTicket(Ticket ticket);


}
