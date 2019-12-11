package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImprotMeterinfo implements Serializable {
     private int rpIssucess;
     private int rpIsUpload;
     private int rpUserCustomerid;
     private int rpMeterid;
     private String rpMeterCode;
     private int rpCurrentReading;
     private int rpUpdateReading;
     private String rpPriorreading;
     private String rpReadTime;

}
