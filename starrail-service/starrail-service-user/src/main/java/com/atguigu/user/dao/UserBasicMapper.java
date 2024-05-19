package com.atguigu.user.dao;


import com.atguigu.model.pojo.UserBasic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserBasicMapper extends BaseMapper<UserBasic> {

    @Select("select * from user_basic where id = #{id}")
    UserBasic getAllById(@Param("id") int id);
}
