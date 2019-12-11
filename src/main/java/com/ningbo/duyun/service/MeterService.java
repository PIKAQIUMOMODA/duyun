package com.ningbo.duyun.service;

import com.alibaba.fastjson.JSON;
import com.ningbo.duyun.bean.*;
import com.ningbo.duyun.dao.MeterMapper;
import com.ningbo.duyun.util.DuyunHttpUtil;
import com.ningbo.duyun.util.HttpUtil;
import com.ningbo.duyun.util.UserAndReadingUtil;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MeterService {

    /**
     * 获取日志记录类
     */
    private static final Logger logger= LoggerFactory.getLogger (MeterService.class);

    private  static Calendar calendar=Calendar.getInstance();
    private  static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    MeterMapper meterMapper;

    @Autowired
    ImportInfoService importInfoService;

    /**
     * 插入远程数据库读数信息
     * @return
     */
    public boolean insertMeterReading(String startTime,String endTime)
    {
        String meterReadingString=UserAndReadingUtil.getWaterReading(startTime,endTime);
        if(meterReadingString==null||"".equals(meterReadingString)) {
            logger.info("获取水表读数失败");
            return false;
        }
        logger.info("获取的数据:"+meterReadingString);
        MeterReadInfo meterReadInfo=null;
        try {
           meterReadInfo = JSON.parseObject(meterReadingString,MeterReadInfo.class);

        }catch (Exception e)
        {
            logger.info("转化对象失败："+e.getMessage());
        }
        System.out.println(meterReadInfo.getTotalCount());
        String s=  meterReadInfo.getTotalCount();
        logger.info("设备数量："+s);
        if(meterReadInfo!=null)
        {
             if(Integer.parseInt(meterReadInfo.getTotalCount())>0) {
                 //数据插入数据库
                 Meter meter = new Meter();
                 int num = 0;
                 for (MeterReading meterReading : meterReadInfo.getReadings()) {
                     meter.setMeterMetercode(meterReading.getMeterCode());
                     meter.setMeterCurrentreading(meterReading.getCurrentReading());
                     meter.setMeterPressure(meterReading.getPressure());
                     meter.setMeterValveStatus(meterReading.getValveStatus());
                     meter.setMeterSignalstrength(meterReading.getSignalStrength());
                     meter.setMeterReadTime(meterReading.getReadTime());
                     meter.setMeterReadDate(meterReading.getReadTime());
                     meter.setMeterReadstatus(meterReading.getReadStatus());
                     meter.setMeterTodayconsume(meterReading.getMeterTodayconsume());
                     meter.setMeterBatteryval(meterReading.getBatteryVal());
                     meter.setMeterImporttime(simpleDateFormat.format(calendar.getTimeInMillis()));
                     meter.setMetetIssuccessimport(0);

                     if (meterMapper.insetMeterReading(meter) > 0) {
                         logger.info("插入读数成功,第" + (++num) + "个,表号:" + meter.getMeterMetercode() + ",读数:" + meter.getMeterCurrentreading() + ",抄表日期:" + meter.getMeterReadTime());
                     }


                 }
             }

        }


        return true;
    }

    /***
     * 从中间库获取待传数据。
     * @return
     */
    private List<RemoteData> getTransferData()
    {
        return meterMapper.getMeterReading();
    }

    /**
     *  上传远程数据
     */
    public Map<String,Object> uploadRemoteData()
    {

        List<FaildItem> faildItemCurrentLessThanPeriod=new ArrayList<>();//本期小于上期
        List<FaildItem> faildItemNotExitsModifier=new ArrayList<>();//不存在可以修改的抄表数据！
        List<FaildItem> faildItemOther=new ArrayList<>();//其他异常

        String duyunUrl="http://47.92.204.93:8001/api/v1/remoteMeter/reading";
        //从中间库提取数据
        List<RemoteData> remoteDataList=getTransferData();
        if(remoteDataList!=null&&remoteDataList.size()>0)
        {
            RemoteRecord remoteRecord=new RemoteRecord();
            remoteRecord.setUserId(225);
            remoteRecord.setDeviceId("NB");
            int sum=remoteDataList.size();
            logger.info("获取的抄表数量是:"+sum+"条");
            //求循环次数
            int i=0;
            if((sum%3000)>0)
            {
                logger.info("进入不能整除区域");
                i=(sum/3000)+1;
               int lastNum=sum%3000;
               int n=0;

                for(int j=0;j<i;j++)
                {
                    if(j==i-1) {//最后一次
                        logger.info("轮到第"+(++n)+"次"+(j*3000)+"到"+sum+"条");
                        remoteRecord.setRecords(remoteDataList.subList(j*3000,sum));
                    }
                    else
                    {

                        logger.info("轮到第"+(++n)+"次"+(j*3000)+"到"+((j+1)*3000)+"条");
                        remoteRecord.setRecords(remoteDataList.subList(j * 3000,(j+1)*3000));
                    }
                    String json=JSON.toJSONString(remoteRecord);
                    logger.info(json);
                    uploadReadering(duyunUrl,json,faildItemCurrentLessThanPeriod,faildItemNotExitsModifier,faildItemOther,n);
                }

            }
            else //
            {

                logger.info("进入能整除区域");
                i=sum/3000;
                int n=0;

                for(int j=0;j<i;j++)
                {

                    logger.info("轮到第"+(++n)+"次"+(j*3000)+"到"+((j+1)*3000)+"条");
                    remoteRecord.setRecords(remoteDataList.subList(j * 3000,(j+1)*3000));
                    String json=JSON.toJSONString(remoteRecord);
                //    uploadReadering(duyunUrl,json,faildItemCurrentLessThanPeriod,faildItemNotExitsModifier,faildItemOther,n);

                }
            }

        }
       HashMap<String,Object> result=new HashMap<>();
       int  CurrentLessThanPeriod=insertErrorInfo(faildItemCurrentLessThanPeriod);
       int NotExitsModifier= insertErrorInfo(faildItemNotExitsModifier);
       int Other= insertErrorInfo(faildItemOther);
       result.put("CurrentLessThanPeriod",CurrentLessThanPeriod);
       result.put("NotExitsModifier",NotExitsModifier);
       result.put("Other",Other);

//        //  把异常信息添加到数据库
//        ImportInfo importInfoTypeCurrentLessThanPeriod=new ImportInfo();//type值为1
//        for(int i=0;i<faildItemCurrentLessThanPeriod.size();i++)
//        {
//            FaildItem faildItem=faildItemCurrentLessThanPeriod.get(i);
//            importInfoTypeCurrentLessThanPeriod.setIsSuccess('0');//默认失败
//            importInfoTypeCurrentLessThanPeriod.setIsUpload('0');//默认失败
//            importInfoTypeCurrentLessThanPeriod.setType('1');//type值为1代表本期小于上期
//            importInfoTypeCurrentLessThanPeriod.setUserCustomerno(faildItem.getCardId());//用户编号
//            importInfoTypeCurrentLessThanPeriod.setReason(faildItem.getErrorCode());//设置错误代码
//            importInfoTypeCurrentLessThanPeriod.setCreateTime(new Date());
//            importInfoTypeCurrentLessThanPeriod.setMeterReadTime(faildItem.getReadDate());
//        }
//
//        ImportInfo importInfoTypeNotExitsModifier=new ImportInfo();
//        for(int i=0;i<faildItemNotExitsModifier.size();i++)
//        {
//            FaildItem faildItem=faildItemNotExitsModifier.get(i);
//            importInfoTypeNotExitsModifier.setIsSuccess('0');//默认失败
//            importInfoTypeNotExitsModifier.setIsUpload('0');//默认失败
//            importInfoTypeNotExitsModifier.setType('2');//type值为2代表不存在修改的情况
//            importInfoTypeNotExitsModifier.setUserCustomerno(faildItem.getCardId());//用户编号
//            importInfoTypeNotExitsModifier.setReason(faildItem.getErrorCode());//设置错误代码
//            importInfoTypeNotExitsModifier.setCreateTime(new Date());
//            importInfoTypeNotExitsModifier.setMeterReadTime(faildItem.getReadDate());
//        }








         //获取本期小于上期的数据读数加1重新传输
//        logger.info("------------------------------------");
//        logger.info(faildItemCurrentLessThanPeriod.toString());
//        List<RemoteData> faildRemoteData=new ArrayList<RemoteData>();
//        for (int i=0;i<faildItemCurrentLessThanPeriod.size();i++)
//        {
//                 FaildItem faildItem=faildItemCurrentLessThanPeriod.get(i);
//                 RemoteData remoteData=new RemoteData();
//                 remoteData.setBarCode("");
//                 remoteData.setCardId(faildItem.getCardId());
//                 remoteData.setReading(faildItem.getReading()+1);
//                 remoteData.setReadState("正常");
//                 remoteData.setSerialNo("");
//                 remoteData.setReadDate(System.currentTimeMillis());
//                 remoteData.setWater(0);
//                 faildRemoteData.add(remoteData);
//
//        }
//        RemoteRecord remoteRecord1=new RemoteRecord();
//        remoteRecord1.setUserId(225);
//        remoteRecord1.setDeviceId("NB");
//        remoteRecord1.setRecords(faildRemoteData);
//        String json=JSON.toJSONString(remoteRecord1);
//        String responseJson= DuyunHttpUtil.doPost(duyunUrl,json);
//         logger.info("------------------------------------");
//         logger.info(responseJson);
//         logger.info(json);
        return result;
    }


    /**
     * 插入错误信息到数据库
     * @param faildItemList
     * @return
     */
    private  int insertErrorInfo(List<FaildItem> faildItemList)
    {
        //前提是failItemList里面的数据类型是一个类型的
        if(faildItemList!=null&faildItemList.size()>0) {
            int n = faildItemList.size();
            int type=3;
            switch(faildItemList.get(0).getErrorCode())
            {
                case "本次抄码不应小于上次抄码!":
                {
                    type=1;
                }break;
                case "不存在可以修改的抄表数据！":
                {
                    type=2;
                }break;
                default:
                {
                    type=3;
                }
                break;

            }

            ImportInfo importInfoType = new ImportInfo();
            for (int i = 0; i < n; i++) {
                FaildItem faildItem = faildItemList.get(i);
                importInfoType .setIsSuccess("0");//默认失败
                importInfoType .setIsUpload("0");//默认失败
                importInfoType .setType(type);//type值为
                importInfoType .setUserCustomerno(faildItem.getCardId());//用户编号
                importInfoType .setReason(faildItem.getErrorCode());//设置错误代码
                importInfoType .setCreateTime(new Date());
                importInfoType .setMeterReadTime(faildItem.getReadDate().split("T")[0]);
                importInfoService.insertImportInfo(importInfoType);
            }

           return n;
        }
        else
            return 0;
    }

    /**
     * 上传读数
     * @param duyunUrl
     * @param json
     * @param faildItemCurrentLessThanPeriod
     * @param faildItemNotExitsModifier
     * @param faildItemOther
     * @param n
     */
    private void uploadReadering(String duyunUrl,String json,List<FaildItem> faildItemCurrentLessThanPeriod,List<FaildItem> faildItemNotExitsModifier,List<FaildItem> faildItemOther,int n)
    {

        try {

            String responseJson= DuyunHttpUtil.doPost(duyunUrl,json);
            DuyunResponse duyunResponse=JSON.parseObject(responseJson,DuyunResponse.class);
            Iterator<FaildItem> iterable=duyunResponse.getData().getFaildItems().iterator();
            logger.info("第"+n+"保存:"+duyunResponse.getData().getSavedCount()+"条");
            logger.info("保存失败数:"+duyunResponse.getData().getFaildItems().size());

            while(iterable.hasNext())
            {
                FaildItem faildItem= iterable.next();
                switch(faildItem.getErrorCode())
                {
                    case "本次抄码不应小于上次抄码!":
                    {
                        faildItemCurrentLessThanPeriod.add(faildItem);
                    }break;
                    case "不存在可以修改的抄表数据！":
                    {
                        faildItemNotExitsModifier.add(faildItem);
                    }break;
                    default:
                    {
                        faildItemOther.add(faildItem);
                    }
                    break;

                }
            }

        }catch (Exception e)
        {
            logger.info(e.getMessage());
        }
    }
}
