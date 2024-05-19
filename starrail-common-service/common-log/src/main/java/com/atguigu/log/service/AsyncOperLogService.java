package com.atguigu.log.service;

import com.atguigu.model.common.OperLog;

public interface AsyncOperLogService {
    void saveSysOperLog(OperLog log);
}
