package com.atguigu.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录信息")
public class LoginDTO {

    @Schema(description = "登录账号")
    private Integer loginId;

    @Schema(description = "登录密码")
    private String password;
}

