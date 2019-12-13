package com.ningbo.duyun.dao;

import com.ningbo.duyun.bean.FaildItem;
import com.ningbo.duyun.bean.ImportInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ImportInfoMapper {

    @Insert("INSERT INTO improtmeterinfo (`rp_issuccess`, `rp_isupload`, `rp_user_customerid`, `rp_meter_meterid`, `rp_meter_metercode`, " +
            "`rp_meter_currentreading`, `rp_meter_updatereading`, `rp_meter_priorreading`, `rp_meter_readTime`, `rp_createtime`,`rp_reason`, " +
            "`rp_user_customerno`,`rp_type`) VALUES (#{isSuccess},#{isUpload},#{userCustomerid},#{meterMeterid},#{meterMetercode},#{meterCurrentreading},#{meterUpdatereading},#{meterPriorreading},#{meterReadTime},#{createTime},#{reason},#{userCustomerno},#{type})")
    int insertImportInfo(ImportInfo importInfo);

    @Select("select rp_user_customerno as CardId,rp_meter_currentreading as Reading ,rp_meter_readTime as ReadDate from improtmeterinfo where rp_type=1")
    List<FaildItem> getFaildItemList();


}
