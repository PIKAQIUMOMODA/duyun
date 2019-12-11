package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private int userCustomerid;//客户id  自增长
    private String userCustomerno;//客户编码
    private String userCustomername; //客户名称
    private String userCustomeraddr; //客户安装地址
    private String userWaterusage; //用水性质
    private String userReginName; //小区名称
    private String userMetercode; //水表编码，设备唯一标识。
    private String userMetername; //水表名称t
    private String userHasvalve; //是否带阀门
    private String userValuestatus; //阀门状态。0：开阀；1: 关阀；2：阀门异常；-1：不带阀门
    private String userStatus;//水表状态。1：正常；2：停用
    private String userInstalltime; //安装时间。时间格式：yyyy-MM-dd HH:mm:ss如：2017-07-11 12:00:00
    private String userInstalladdr;//安装地址
    private String userCusomerMemo;//备注
    private String userMechanicalcode;//机械表号
    private String userImporttime;//导入时间
    private String userModifiertime;//修改时间
    private String userOldecustomerno;//旧用户编号

}
