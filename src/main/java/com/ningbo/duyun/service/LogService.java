package com.ningbo.duyun.service;

import com.ningbo.duyun.bean.Log;
import com.ningbo.duyun.dao.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private   LogMapper logMapper;

    public int insertLog(Log log)
    {
        return  logMapper.insertLog(log);
    }

    public List<Log> selectByType(int type)
    {
        return logMapper.selectByType(type);
    }

}
