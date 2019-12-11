package com.ningbo.duyun.controller;

import com.ningbo.duyun.bean.ImportInfo;
import com.ningbo.duyun.bean.Log;
import com.ningbo.duyun.dao.LogMapper;
import com.ningbo.duyun.service.ImportInfoService;
import com.ningbo.duyun.service.LogService;
import com.ningbo.duyun.service.MeterService;
import com.ningbo.duyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@RestController
public class IndexController {

    /**
     * 用户数据服务
     */
    @Autowired
    private UserService userService;

    /**
     * 水表数据服务
     */
    @Autowired
    private MeterService meterService;

    @Autowired
    private LogService logService;

    @Autowired
    private ImportInfoService importInfoService;


    /**
     * 更新用户
     * @return
     */
    @GetMapping("/updateUser")
    public String updateUser()
    {

        return "";
    }


    /**
     * 上传读数
     */
    @GetMapping("/uploadReading")
    public String uploadReading(HttpServletRequest request, HttpServletResponse response)
    {
       // meterService.getMeterReading();
       System.out.println(meterService.uploadRemoteData());
        return "";
    }
    @GetMapping("log")
    public int get()
    {
        ImportInfo importInfo=new ImportInfo();
        importInfo.setMeterMetercode("111111111");

        return   importInfoService.insertImportInfo( importInfo);
    }

    @GetMapping("getLog")
    public int getLog(){
       List<Log> logs= logService.selectByType(3);
      return    logs.size();
    }


}
