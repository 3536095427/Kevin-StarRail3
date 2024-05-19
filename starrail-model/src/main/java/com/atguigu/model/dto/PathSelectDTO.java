package com.atguigu.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "路线查询信息")
public class PathSelectDTO {
    @Schema(description = "出发地")
    private String startStation;

    @Schema(description = "目的地")
    private String destination;

    @Schema(description = "出发日期")
    private String startDate;
}
