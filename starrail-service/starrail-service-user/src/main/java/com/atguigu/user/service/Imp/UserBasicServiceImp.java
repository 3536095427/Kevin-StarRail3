package com.atguigu.user.service.Imp;


import com.alibaba.fastjson.JSON;
import com.atguigu.commonservice.utils.AuthContext;
import com.atguigu.user.dao.UserBasicMapper;
import com.atguigu.user.dao.UserDetailMapper;
import com.atguigu.model.dto.LoginDTO;
import com.atguigu.model.pojo.UserBasic;
import com.atguigu.model.pojo.UserDetail;
import com.atguigu.user.service.UserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userBasicService")
@Transactional
public class UserBasicServiceImp implements UserBasicService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserBasicMapper userBasicMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;


    @Override
    public UserBasic Login(LoginDTO loginDTO, String token) {

        UserBasic userBasic = AuthContext.getAuthContext();

        if (userBasic != null) {
            return userBasic;
        }

        // 检测用户是否存在
        userBasic = userBasicMapper.selectById(loginDTO.getLoginId());

        //数据库中不存在这个用户，返回null
        if (userBasic == null) {
            return null;
        }

        //检验密码
        String passwordGet = userBasic.getPassword();

        //如果密码错误，返回null
        if (!loginDTO.getPassword().equals(passwordGet)) {
            return null;
        }

        redisTemplate.opsForValue().set("user:" + token, JSON.toJSONString(userBasic));

        return userBasic;
    }

    @Override
    public UserDetail getUserDetail() {
        UserBasic userBasic = AuthContext.getAuthContext();
        if (userBasic.getUserDetail() == null){
            userBasic.setUserDetail(userDetailMapper.selectById(userBasic.getId()));
        }

        return userBasic.getUserDetail();
    }


};