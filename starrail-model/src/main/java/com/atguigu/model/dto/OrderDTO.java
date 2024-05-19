package com.atguigu.model.dto;

import com.atguigu.model.pojo.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "提交订单信息")
public class OrderDTO {
    @Schema(description = "乘客姓名")
    private String passengerName;

    @Schema(description = "乘客身份证号")
    private String passengerId;

    @Schema(description = "车票信息")
    private Ticket ticket;
}
