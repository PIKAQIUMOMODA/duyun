package com.ningbo.duyun.bean;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImportInfo implements Serializable {
    private String isSuccess;
    private String isUpload;
    private int userCustomerid;
    private int meterMeterid;
    private String meterMetercode;
    private String meterCurrentreading;
    private String meterUpdatereading;
    private String meterPriorreading;
    private String meterReadTime;
    private Date createTime;
    private String reason;
    private String userCustomerno;
    private int type;

}
