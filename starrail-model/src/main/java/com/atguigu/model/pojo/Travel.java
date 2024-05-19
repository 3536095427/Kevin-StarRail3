package com.atguigu.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路线")
public class Travel {

    @Schema(description = "路线id")
    private Integer id;

    @Schema(description = "出发站id")
    private Integer origin;

    @Schema(description = "到达站id")
    private Integer destination;

    @TableField("time")
    @Schema(description = "花费时间")
    private Integer spendTime;

    @Schema(description = "距离")
    private Integer distance;

    @Schema(description = "花费金额")
    private Integer money;

    public Travel(int id) {
        this.id = id;
    }

}
