package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class  Meter implements Serializable {

    private String meterMetercode;
    private String meterCurrentreading;
    private String meterPressure;
    private Integer meterValveStatus;
    private String meterSignalstrength;
    private String meterReadTime;
    private String meterReadDate;
    private Integer meterReadstatus;
    private String meterTodayconsume;
    private String meterBatteryval;
    private String meterImporttime;
    private Integer metetIssuccessimport;

}
