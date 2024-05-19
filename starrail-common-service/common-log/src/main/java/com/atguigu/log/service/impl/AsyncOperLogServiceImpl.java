package com.atguigu.log.service.impl;


import com.atguigu.log.mapper.SysOperLogMapper;
import com.atguigu.model.common.OperLog;
import com.atguigu.log.service.AsyncOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {


    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void saveSysOperLog(OperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
