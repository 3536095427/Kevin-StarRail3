package com.atguigu.travel.dao;


import com.atguigu.model.pojo.Travel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TravelMapper extends BaseMapper<Travel> {

    @Select(" SELECT destination FROM travel WHERE travel.orgin = #{id} ORDER BY travel.destination ASC")
    @ResultType(Integer.class)
    Integer[] getDestinationsIdByID(Integer id);


    @Select("SELECT distance FROM travel WHERE travel.destination = #{destination} && travel.orgin = #{OrginId}")
    @ResultType(Integer.class)
    Integer getDistancesByID(Integer destination, Integer OrginId);

    @Select("SELECT money FROM travel WHERE travel.destination = #{destination} && travel.orgin = #{OrginId}")
    @ResultType(Integer.class)
    Integer getMoneyByID(Integer destination, Integer OrginId);

    @Select("SELECT time FROM travel WHERE travel.destination = #{destination} && travel.orgin = #{OrginId}")
    @ResultType(Integer.class)
    Integer getTimeByID(Integer destination, Integer OrginId);
}
