package com.ningbo.duyun.bean;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: java类作用描述
 * @author:wm
 * @date: 2019/2/19 11:31
 */
@Data
public class RemoteRecord implements Serializable {

    private int userId; //用户id "225"
    private String deviceId;//设备id NB
    private List<RemoteData> records;

}
