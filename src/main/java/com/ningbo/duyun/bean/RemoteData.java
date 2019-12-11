package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @author:wm
 * @date: 2019/2/19 11:24
 */
@Data
public class RemoteData implements Serializable {
    private  String cardId;//客户编号
    private  String barCode;//空值
    private  String serialNo;//空值
    private  int    reading;//本次抄码整数
    private  int    water;//空值
    private  String readState;//正常
    private  Long   readDate;//抄表日期时间戳

}
