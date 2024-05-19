package com.atguigu.travel.service;


import com.atguigu.model.dto.PathSelectDTO;
import com.atguigu.model.pojo.Ticket;

import java.util.List;

public interface TravelService {


    int[] getPathArray(String type, String beginStation, String destinationStation);

    int[] getPathByArray(int[] front, Integer start, Integer destination);

    int getTotalLenByPath(int[] pathArray);

    int getTotalMoneyByPath(int[] pathArray);

    int getTotalTimeByPath(int[] pathArray);

    String getPathStationInfo(int[] pathArray);

    List<Ticket> getTicketFactor(PathSelectDTO dot);
}
