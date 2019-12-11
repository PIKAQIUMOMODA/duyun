package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeviceInfoRespone implements Serializable {

    String totalCount;
    List<DeviceInfo> devices;
}
