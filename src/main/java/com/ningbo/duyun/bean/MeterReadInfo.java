package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 *
 *   水表结构初始读数
 * @author jwj
 * @date 2018/5/16
 */
@Data
public class MeterReadInfo implements Serializable {
    /**
     * 读数总计
     */
    private String totalCount;
    /**
     * 水表读数
     */
    private List<MeterReading> readings;


}
