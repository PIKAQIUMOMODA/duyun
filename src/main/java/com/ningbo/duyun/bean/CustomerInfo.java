package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @author:wm
 * @date: 2019/2/21 15:21
 */
@Data
public class CustomerInfo  implements Serializable {
    /**
     * 厂家ID 宁波水表为1
     */
    private int FactoryId;
    /**
     * 水表编码为每个厂家的唯一标识
     */
    private  String  MeterAddr;
    /**
     *业务软件里面客户的唯一标识
     */
    private int MeterId;
    /**
     * 客户名称
     */
    private  String  UserName;
    /**
     * 联系人
     */
    private  String  Linkman;
    /**
     * 联系电话
     */
    private  String  Phone;
    /**
     * 身份证号码
     */
    private  String  PaperNo;
    /**
     * 详细地址
     */
    private  String  Address;
    /**
     * 水表口径
     */
    private  String  Caliber;
    /**
     * 装表时间
     */
    private  String  InstallDate;
    /**
     * 是否阀控表 0：否；1：是
     */
    private  String  IfCtrlValve;
    /**
     *默认值为：0，导入之后变为：1
     * 由成聪软件更新
     */
    private  int  Imported;
    /**
     * 备注
     */
    private  String  Remark;
    /**
     * 旧表ID
     */
    private   int   OldMeterID;
    /**
     * 客户编码,在远程水表系统里面的客户编码
     */
    private  String  UserCode;
}
