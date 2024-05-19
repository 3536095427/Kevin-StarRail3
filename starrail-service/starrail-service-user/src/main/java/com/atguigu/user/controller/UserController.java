package com.atguigu.user.controller;


import com.atguigu.log.annoation.Log;
import com.atguigu.model.common.Result;
import com.atguigu.model.common.ResultCodeEnum;
import com.atguigu.model.dto.LoginDTO;
import com.atguigu.model.pojo.UserBasic;
import com.atguigu.model.pojo.UserDetail;
import com.atguigu.user.service.UserBasicService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    @Qualifier("userBasicService")
    private UserBasicService userBasicService;

    @Resource
    private RedisTemplate<String ,String> redisTemplate;


    @Operation(summary = "登录跳转")
    @GetMapping(value = {"/", "/index"})
    public String loginRedirect() {
        return "redirect:/api/user/login";
    }


    @Operation(summary = "用户登录")
    @PostMapping("/login")
    @Log(title = "用户登录", businessType = 1)
    public Result<Boolean> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        UserBasic user = userBasicService.Login(loginDTO, request.getHeader("token"));

        if (user == null) {
            return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
        }
        return Result.build(true, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/auth/userInfo")
    @Log(title = "获取用户信息", businessType = 1)
    public Result<UserDetail> userInfo() {
        return Result.build(userBasicService.getUserDetail(), ResultCodeEnum.SUCCESS);
    }
}
