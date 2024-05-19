package com.atguigu.feign.travelpath;

import com.atguigu.model.common.Result;
import com.atguigu.model.dto.PathSelectDTO;
import com.atguigu.model.pojo.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient("starrail-service-travelpath")
public interface TravelPathClient {

    @PostMapping(value = "/api/travelpath/getTicketFactor")
    Result<List<Ticket>> getTicketFactor(@RequestBody PathSelectDTO dto);
}
