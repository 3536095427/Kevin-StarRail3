package com.atguigu.commonservice.handler;


import com.atguigu.commonservice.exception.StarRailException;
import com.atguigu.model.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
// 根据系统内部抛出的异常，通过
// @ExceptionHandler 匹配并捕获异常，
// 返回自定义的异常信息
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null,201, "系统内部错误");
    }

    @ExceptionHandler(value = StarRailException.class)     // 处理自定义异常
    @ResponseBody
    public Result error(StarRailException exception) {
        exception.printStackTrace();
        return Result.build(null , exception.getResultCodeEnum()) ;
    }
}
