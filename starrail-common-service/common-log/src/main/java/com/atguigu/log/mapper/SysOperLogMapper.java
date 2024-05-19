package com.atguigu.log.mapper;

import com.atguigu.model.common.OperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogMapper extends BaseMapper<OperLog> {
    int insert(OperLog sysOperLog);
}
