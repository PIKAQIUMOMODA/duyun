package com.ningbo.duyun.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ningbo.duyun.result.Result;
import com.ningbo.duyun.service.MeterService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public  class HttpUtil {
    /**
     * 获取日志记录类
     */
    private static final Logger logger= LoggerFactory.getLogger (HttpUtil.class);

    public static String httpPost(Map<String,String> objectMap,String requestUrl,String accessToken) throws Exception
    {
        if(requestUrl==null||"".equals(requestUrl))
            throw new Exception("请求的url不能为空");
        //设置请求超时为20秒
        OkHttpClient client=new OkHttpClient().newBuilder().callTimeout(20L, TimeUnit.SECONDS).build();
        Request request;
        if(objectMap==null) {
            request= new Request.Builder().url(requestUrl).addHeader("Content-Type","application/json").build();
        }
        else {
            ObjectMapper objectMapper=new ObjectMapper();
            String  body=objectMapper.writeValueAsString(objectMap);
            logger.info(body);
            MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody=RequestBody.create(mediaType,body);
            if(accessToken!=null&&!"".equals(accessToken))
                request = new Request.Builder().url(requestUrl).addHeader("Content-Type","application/json").addHeader("Authorization",accessToken).post(requestBody).build();
            else
                request = new Request.Builder().url(requestUrl).addHeader("Content-Type","application/json").post(requestBody).build();
        }

        try (Response response = client.newCall(request).execute()) {
         return  response.body().string();
        }
        catch (Exception e)
        {
            return Result.REQUEST_FAILED.toString();
        }



    }

    public static String httpGet(String requestUrl,String accessToken) throws Exception
    {

        //设置请求超时为240秒
        OkHttpClient client=new OkHttpClient().newBuilder().readTimeout(240L,TimeUnit.SECONDS).writeTimeout(240L,TimeUnit.SECONDS).callTimeout(240L, TimeUnit.SECONDS).build();
        if(accessToken!=null&&!"".equals(accessToken)) {
            Request request = new Request.Builder().url(requestUrl).addHeader("Authorization", accessToken).build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (Exception e) {
                logger.info("get请求失败:"+e.getMessage());
                return null;
            }

        }
        return null;
    }



}
