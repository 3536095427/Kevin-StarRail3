package com.atguigu.commonservice.exception;


import com.atguigu.model.common.ResultCodeEnum;
import lombok.Data;

// 自定义异常类
@Data
public class StarRailException extends RuntimeException{

    private Integer code ;          // 错误状态码
    private String message ;        // 错误消息

    private ResultCodeEnum resultCodeEnum ;     // 封装错误状态码和错误消息

    public StarRailException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public StarRailException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
