package com.ningbo.duyun.util;

import com.alibaba.fastjson.JSON;
import com.ningbo.duyun.bean.AccessTokenBean;
import com.ningbo.duyun.result.Result;
import com.ningbo.duyun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public  class UserAndReadingUtil {

    /**
     * 获取日志记录类
     */
    private static final Logger logger= LoggerFactory.getLogger (UserAndReadingUtil.class);


    private static String  companyCode;
    private static String startTime;
    private static String endTime;

    @Value("${duyun.companyCode}")
    private void setCompanyCode(String companyCode) {
       this.companyCode=companyCode;
    }

    @Value("${duyun.startTime}")
    private void setStartTime(String startTime)
    {
        this.startTime=startTime;
    }

    @Value("${duyun.endTime}")
    private void setEndTime(String endTime)
    {
        this.endTime=endTime;
    }

    /**
     * 获取鉴权数据
     * @return
     */
    private   static Map<String,String> getToken()
    {
        String accessUrl="http://116.62.124.32:25000/znbpt/v1.0.0/login";
        Map<String,String> userMap=new HashMap<>();
        userMap.put("appId","宁远自来水");
        userMap.put("secret","nyzls");
        Map<String,String> accessMap=new HashMap<>();
        try {
            String accessToken= HttpUtil.httpPost(userMap,accessUrl,null);
            logger.info("获取accessToken:"+accessToken);
            if(accessToken!=null) {
                try {
                    AccessTokenBean accessTokenBean= JSON.parseObject(accessToken,AccessTokenBean.class);
                    accessMap.put("expires",accessTokenBean.getExpires());
                    accessMap.put("accessToken",accessTokenBean.getAccessToken());
                } catch (Exception e) {
                    logger.info("转化对象失败,获取accessToken对象失败");
                }
            }

        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }

          return accessMap;
    }

    /**
     * 获取用户数据
     * @return
     */
    public  static String getCustomerInfo()
    {

        Map<String,String> accessMap=getToken();
        String userInfo=new String();
        if(accessMap!=null&&accessMap.size()>0)
        {
            String getUser="http://116.62.124.32:25000/znbpt/v1.0.0/devices?companyCode="+companyCode;

            try {
               userInfo=HttpUtil.httpGet(getUser,accessMap.get("accessToken"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else
        {
            logger.info("获取权限失败");
        }
        return userInfo;
    }

    /**
     * 获取水表数据
     * @return
     */
/*    public static String getWaterInfo()
    {
     Map<String,String> accessMap=getToken();
        String waterInfo=null;
        if(accessMap!=null&&accessMap.size()>0)
        {

            String getReading="http://116.62.124.32:25000/znbpt/v1.0.0/readings?companyCode="+companyCode+"&startTime="+startTime+"&endTime="+endTime;
            try {
                logger.info("正在获取水表数据");
                waterInfo=HttpUtil.httpGet(getReading,accessMap.get("accessToken"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("获取水表数据失败！"+e.getMessage());
            }

        }
        else
        {
            logger.info("获取权限失败");
        }
        return waterInfo;
    }*/

    public static String getWaterReading(String startTime,String endTime) {
        Map<String,String> accessMap=getToken();
        String waterInfo = null;
        if(accessMap!=null&&accessMap.size()>0)
        {

        String getReading = "http://116.62.124.32:25000/znbpt/v1.0.0/readings?companyCode=" + companyCode + "&startTime=" + startTime + "&endTime=" + endTime;
        try {
            logger.info("正在获取水表数据");

            waterInfo = HttpUtil.httpGet(getReading, accessMap.get("accessToken"));
            try {
                //如果转化成功
                JSON.parseObject(waterInfo, Result.class);
                return null;
            } catch (Exception e) {
                return waterInfo;
            }

        } catch (Exception e) {
            logger.info("获取水表数据失败！" + e.getMessage());
            return null;
        }


        }
       else
        {
            logger.info("获取权限失败");
        }
        return waterInfo;
    }


}
