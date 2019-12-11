package com.ningbo.duyun.controller;


import com.ningbo.duyun.bean.Log;
import com.ningbo.duyun.service.ImportInfoService;
import com.ningbo.duyun.service.LogService;
import com.ningbo.duyun.service.MeterService;
import com.ningbo.duyun.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * 上传用户
     * @return
     */
    @GetMapping("/updateUser")
    public Model updateUser(Model model) {
        boolean result = userService.addUserInfo();
        model.addAttribute("上传用户", result);
        return model;
    }


    /**
     * 上传读数
     */
    @GetMapping("/uploadReading")
    public Model uploadReading(Model model, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> result = meterService.uploadRemoteData();
        model.addAttribute("CurrentLessThanPeriod", result.get("CurrentLessThanPeriod"));
        model.addAttribute("CurrentLessThanPeriod", result.get("CurrentLessThanPeriod"));
        model.addAttribute("CurrentLessThanPeriod", result.get("CurrentLessThanPeriod"));
        return model;
    }


    @GetMapping("getLog/${type}")
    public int getLog(@Param("type")int type) {
        List<Log> logs = logService.selectByType(type);
        return logs.size();
    }

    /**
     * 根据时间插入读数到中间库
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/getReadingData")
    public Model getReadingData(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startTime = request.getAttribute("startTime").toString();
        String endTime = request.getAttribute("endTime").toString();
        boolean result = meterService.insertMeterReading(startTime, endTime);
        model.addAttribute("result", result);
        return model;
    }


}
