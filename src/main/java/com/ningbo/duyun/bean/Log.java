package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Log  implements Serializable {
    private int logType; //1:插入读数信息错误2，插入用户信息错误3，上传读数失败
    private String logContent;//日志内容
    private Date logCreatetime;//创建时间
}
