package com.atguigu.model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order")
@ToString
@Schema(description = "订单")
public class Order {
    @Schema(description = "订单id")
    private String orderId;
    @Schema(description = "订单所属用户")
    private UserBasic owner;
    @Schema(description = "乘客姓名")
    private String passengerName;
    @Schema(description = "乘客身份证号")
    private String passengerId;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Schema(description = "生成时间")
    private LocalDateTime generateTime;
    @Schema(description = "支付状态")
    private int paymentStatus;
    @Schema(description = "车票信息")
    private Ticket ticket;


    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }
}
