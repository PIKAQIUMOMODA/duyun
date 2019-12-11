package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class FaildItem implements Serializable {
    boolean CardExist;
    boolean ReadStateError;
    boolean WaterError;
    boolean CanRead;
    String ErrorCode;
    int EntryState;
    String CardId;
    int Reading;
    int ReadWater;
    int ReadState;
    String ReadStateCn;
    String ReadDate;
    String BarCode;
    String SealNumber;
    String CardAddress;
    int RecordLgLdState;
    boolean IsRead;
    int ReasonUNRead;
}
