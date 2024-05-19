package com.atguigu.travel.dao;


import com.atguigu.model.pojo.Station;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StationMapper extends BaseMapper<Station> {

    Station getStationById(@Param("id") int id);

    @Select("select name from station where id = #{id};")
    String getStationNameById(@Param("id") int id);
}
