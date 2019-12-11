package com.ningbo.duyun.dao;

import com.ningbo.duyun.bean.Meter;

import com.ningbo.duyun.bean.RemoteData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("MeterMapper")
public interface MeterMapper {
    /**
     * 插入读数
     * @param
     * @return
     */
    int insetMeterReading(Meter meter);

    /**
     * 获取每只表的最近一次读数
     * @return
     */
    List<RemoteData> getMeterReading();

    /**
     * 删除数据
     * @return
     */
    int deleteMeterReading();
}
