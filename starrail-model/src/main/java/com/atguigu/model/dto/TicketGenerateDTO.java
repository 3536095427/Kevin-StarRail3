package com.atguigu.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "车票生成信息")
public class TicketGenerateDTO {

    @Schema(description = "路径选择信息")
    private PathSelectDTO pathSelectDTO;

    @Schema(description = "生成数量")
    private int generateNum;
}
