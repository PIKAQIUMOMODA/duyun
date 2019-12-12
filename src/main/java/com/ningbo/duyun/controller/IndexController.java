package com.ningbo.duyun.controller;


import com.ningbo.duyun.bean.Log;
import com.ningbo.duyun.service.ImportInfoService;
import com.ningbo.duyun.service.LogService;
import com.ningbo.duyun.service.MeterService;
import com.ningbo.duyun.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
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
    @ResponseBody
    public Map<String,String> updateUser() {
        long startTime=System.currentTimeMillis();//毫秒
      int result=1;  //userService.addUserInfo()?1:0;
        long endTime=System.currentTimeMillis();//毫秒
        long useTime=(60000000)/(60*1000);//分钟
        Map<String,String > map= new HashMap<String,String>();
        map.put("result",Integer.toString(result));
        map.put("useTime",Long.toString(useTime));
        return map;

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


    @GetMapping("getLog/{type}")
    public int getLog(@Param("type")int type) {
        List<Log> logs = logService.selectByType(type);
        return logs.size();
    }

    /**
     * 根据时间插入读数到中间库
     * @param startTime
     * @param endTime
     * @return
     * @throws IOException
     */
    @GetMapping("/getReadingData")
    @ResponseBody
    public String getReadingData(String startTime,String endTime) throws IOException {
         System.out.println(startTime);
         System.out.println(endTime);
         long start=1;
         long end=1;
         Map<String ,String> map=new HashMap<>();
        if(start<=end)
        {
            map.put("result","1");
        }
        else
        {
            map.put("result","0");
        }
        return map.toString();
    }

    @GetMapping("index")
    public String index()
    {
        return "index.html";
    }


}
