package com.atguigu.user.service;


import com.atguigu.model.dto.LoginDTO;
import com.atguigu.model.pojo.UserBasic;
import com.atguigu.model.pojo.UserDetail;

public interface UserBasicService {
    UserBasic Login(LoginDTO dto,String token);

    UserDetail getUserDetail();


}
