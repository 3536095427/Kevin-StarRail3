package com.atguigu.model.pojo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "车票")
public class Ticket {

    @Schema(description = "车票id")
    private String ticketId;

    @Schema(description = "出发车站")
    private String startStation;

    @Schema(description = "到达车站")
    private String destinationStation;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Schema(description = "出发时间")
    private LocalDateTime startTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Schema(description = "到达时间")
    private LocalDateTime arrivalTime;

    @Schema(description = "车次信息")
    private String trainInfo;

    @Schema(description = "座位信息")
    private String seatInfo;

    @Schema(description = "距离")
    private int distance;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @Schema(description = "时间")
    private LocalTime time;

    @Schema(description = "价格")
    private int price;

    @Schema(description = "路径信息")
    private String pathInfo;

    public Ticket(String startStation, String destinationStation, LocalDateTime startTime, int distance, LocalTime time, int price, String pathInfo,String trainInfo,String seatInfo) {
        this.startStation = startStation;
        this.destinationStation = destinationStation;
        this.startTime = startTime;
        this.distance = distance;
        this.time = time;
        this.price = price;
        this.pathInfo = pathInfo;
        this.seatInfo = seatInfo;
        this.trainInfo = trainInfo;
    }


    public Ticket(String startStation, String destinationStation, LocalDateTime startTime, int distance, LocalTime time, int price, String pathInfo) {
        this.startStation = startStation;
        this.destinationStation = destinationStation;
        this.startTime = startTime;
        this.distance = distance;
        this.time = time;
        this.price = price;
        this.pathInfo = pathInfo;
    }

}
