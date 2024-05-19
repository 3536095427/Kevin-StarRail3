package com.atguigu.model.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户")
public class UserBasic {
    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "密码")
    private String password;

    @TableField(exist = false)
    @Schema(description = "用户详细信息")
    private UserDetail userDetail;

    public UserBasic(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
