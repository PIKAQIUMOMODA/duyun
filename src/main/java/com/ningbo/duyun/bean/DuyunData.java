package com.ningbo.duyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class DuyunData implements Serializable {
     int savedCount;
     List<FaildItem> faildItems;
}
