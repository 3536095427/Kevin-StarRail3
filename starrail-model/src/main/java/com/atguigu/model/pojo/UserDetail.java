package com.atguigu.model.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "用户详细信息")
public class UserDetail {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "个性签名")
    private String moto;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "头像")
    private String img;
}






