package com.ningbo.duyun.bean;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImportInfo implements Serializable {
    private char isSuccess;
    private char isUpload;
    private int userCustomerid;
    private int meterMeterid;
    private String meterMetercode;
    private double meterCurrentreading;
    private double meterUpdatereading;
    private double meterPriorreading;
    private String meterReadTime;
    private Date createTime;
    private String reason;
    private String userCustomerno;
    private int type;

}
