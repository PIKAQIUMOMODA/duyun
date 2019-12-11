package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * znbpt平台上的水表读数
 * @author jwj
 * @date 2018/5/16

 */
@Data
public class MeterReading implements Serializable {
    /**
     * 水表编码
     */
    private String meterCode;
    /**
     * 当前读数
     */
    private String currentReading;
    /**
     * 压力值
     */
    private String pressure;
    /**
     * 阀门状态
     */
    private int valveStatus;
    /**
     *信号强度
     */
    private String signalStrength;
    /**
     * 抄读时间
     */
    private String  readTime;
    /**
     * 间隔流量
     */
    //  private String intervalFlows;
    /**
     * 抄读状态
     */
    private Integer readStatus;

    /**
     * 电压
     */
    private String batteryVal;


    private String meterTodayconsume;




}
