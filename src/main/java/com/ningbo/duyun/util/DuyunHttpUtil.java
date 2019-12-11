package com.ningbo.duyun.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
*
*类功能描述:http请求
*@author:wm
*@date:2018/11/13
*/
public class DuyunHttpUtil {
    private static  Logger logger= LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 方法功能描述:发送http post请求
     * @param url 请求地址
     * @param jsonParms 请求参数
     * @param token 鉴权
     * @return
     */
    public static String doPost(String url,String jsonParms ){
        HttpClient httpClient= HttpClients.createDefault();
        org.apache.http.HttpResponse httpResponse=null;
        HttpPost httpPost=null;
        BufferedReader reader=null;
        StringBuilder builder=null;
        try {
            httpPost=new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity entity=new StringEntity(jsonParms,"UTF-8");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8"));
            entity.setContentEncoding("UTF-8");
            httpPost.setEntity(entity);
//            if (token!=null&&!token.equals("")){
//                httpPost.setHeader("Authorization",token);
//            }
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build());
            httpResponse=httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode()==200){
                String temp=null;
                builder=new StringBuilder();
                reader=new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                while ((temp=reader.readLine())!=null){
                    builder.append(temp);
                    temp=null;
                }
               // logger.info(builder.toString());
                return  builder.toString();
            }else {
                logger.info("状态码不对"+httpResponse.getStatusLine().getStatusCode());
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage(),e);
            return null;
        }
    }

    public static String doGet(String url,String jsonParms,String token){
        HttpClient httpClient=HttpClients.createDefault();
        HttpResponse response=null;
        HttpGet httpGet=null;
        BufferedReader reader=null;
        StringBuilder builder=null;
        try {
            httpGet=new HttpGet(url);
//            StringEntity entity=new StringEntity(jsonParms);
//            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            if (token!=null&&!token.equals("")){
                httpGet.setHeader("Authorization",token);
            }
            httpGet.setConfig(RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build());
            response=httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                String temp=null;
                builder=new StringBuilder();
                reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
                while ((temp=reader.readLine())!=null){
                    builder.append(temp);
                    temp=null;
                }
                return  builder.toString();
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
