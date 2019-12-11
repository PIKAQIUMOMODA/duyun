package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceInfo implements Serializable {
    private String customerNo;//客户编码
    private String customerName; //客户名称
    private String customerAddr; //客户安装地址
    private String waterUsage; //用水性质
    private String regionName; //小区名称
    private String meterCode; //水表编码，设备唯一标识。
    private String meterName; //水表名称t
    private String hasValve; //是否带阀门
    private String valveStatus; //阀门状态。0：开阀；1: 关阀；2：阀门异常；-1：不带阀门
    private String status;//水表状态。1：正常；2：停用
    private String installTime; //安装时间。时间格式：yyyy-MM-dd HH:mm:ss如：2017-07-11 12:00:00
    private String installAddr;//安装地址
    private String cusomerMemo;//备注
    private String mechanicalCode;//机械表号
}
