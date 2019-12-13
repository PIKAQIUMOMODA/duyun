package com.ningbo.duyun.controller;


import com.ningbo.duyun.bean.Log;
import com.ningbo.duyun.service.ImportInfoService;
import com.ningbo.duyun.service.LogService;
import com.ningbo.duyun.service.MeterService;
import com.ningbo.duyun.service.UserService;
import com.ningbo.duyun.util.UserAndReadingUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
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

    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);

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
        Map<String, String> map = new HashMap<String, String>();
        try {
            long startTime = System.currentTimeMillis();//毫秒
            int result = userService.addUserInfo() ? 1 : 0;
            long endTime = System.currentTimeMillis();//毫秒
            long useTime = (endTime - startTime) / (60 * 1000);//分钟
            map.put("result", Integer.toString(result));
            map.put("useTime", Long.toString(useTime));
        }catch (Exception e)
        {
            logger.error("上传用户失败！"+e.getMessage());
        }

        return map;

    }


    /**
     * 上传读数
     */
    @GetMapping("/uploadReading")
    @ResponseBody
    public  Map<String,Integer> uploadReading() {
         Map<String,Integer>  model=new HashMap<String, Integer>();
         try {
             Map<String, Object> result = meterService.uploadRemoteData();
             model.put("CurrentLessThanPeriod", Integer.valueOf(result.get("CurrentLessThanPeriod").toString()));
             model.put("CurrentLessThanPeriod", Integer.valueOf(result.get("CurrentLessThanPeriod").toString()));
             model.put("CurrentLessThanPeriod", Integer.valueOf(result.get("CurrentLessThanPeriod").toString()));
         }catch (Exception e)
         {
             logger.error("上传读数出错"+e.getMessage());
         }
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
    public Map<String,Integer> getReadingData(String startTime, String endTime) throws IOException {
        Map<String ,Integer> map=new HashMap<>();
       try {
           if ("".equals(startTime) || "".equals(endTime)) {
               //时间不能为空
               map.put("result", 2);
               return map;
           }

           long start = System.currentTimeMillis();
           boolean result = false;
           try {
               result = meterService.insertMeterReading(startTime, endTime);
               Thread.sleep(1000);//休眠1000ms
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           long end = System.currentTimeMillis();
           if (result) {
               map.put("result", 1);
           } else {
               map.put("result", 0);
           }
           long useTime = (end - start) / (60 * 1000);//算分钟
           //   Integer.toString();
           map.put("useTime", (int) (useTime));
       }catch (Exception e)
       {
           logger.error("插入中间库失败"+e.getMessage());
           map.put("result",3);
       }
        return map;
    }

    @GetMapping("index")
    public String index()
    {
        return "index";
    }


}
