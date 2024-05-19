package com.atguigu.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "车站")
public class Station {

    @Schema(description = "车站id")
    private Integer id;
    @Schema(description = "车站名称")
    private String name;

    @TableField(exist = false)
    @Schema(description = "车站已有路线")
    private List<Travel> travels;

    public Station(Integer id) {
        this.id = id;
    }

}