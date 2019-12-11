package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DuyunResponse implements Serializable {
    private  String code;
    private  String message;
    private  DuyunData data;
}
