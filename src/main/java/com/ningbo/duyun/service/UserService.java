package com.ningbo.duyun.service;

import com.alibaba.fastjson.JSON;
import com.ningbo.duyun.bean.DeviceInfo;
import com.ningbo.duyun.bean.DeviceInfoRespone;
import com.ningbo.duyun.bean.User;
import com.ningbo.duyun.dao.UserMapper;
import com.ningbo.duyun.util.ListClass;
import com.ningbo.duyun.util.UserAndReadingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    /**
     * 获取日志记录类
     */
    private static final Logger logger= LoggerFactory.getLogger (UserService.class);

    private  static   Calendar calendar=Calendar.getInstance();
    private  static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private UserMapper userMapper;

    //事务管理器
    @Autowired
    private  DataSourceTransactionManager transactionManager;
    /**
     * 插入用户信息
     * @param user
     * @return
     */
   public   int insertUserInfo(User user){
        return userMapper.insertUserInfo(user);
    }

    /**
     * 通过用户编号更新数据
     * @param user
     * @return
     */
    public  int updateUserInfoByCustomerNo(User user){
        return userMapper.updateUserInfoByCustomerNo(user);
    }

    /**
     * 查询用户信息
     * @return
     */
   public List<User> selectUserInfo(){
        return userMapper.selectUserInfo();
    }

    /**
     * 通过用户编号查询用户信息
     * @param customerNo
     * @return
     */
   public   User selectUserByCustomerNo(String customerNo){
        return userMapper.selectUserByCustomerNo(customerNo);
    }

    /**
     * 通过用户id删除用户信息
     * @param id
     * @return
     */
   public int delteUserByCustomerId(int id){
        return userMapper.delteUserByCustomerId(id);
    }

    public int insertUser()
    {

        DefaultTransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        transactionDefinition.setName("Transaction-name-addUser");
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status=transactionManager.getTransaction(transactionDefinition);

        int num=0;
          String userInfo="{\n" +
                  "    \"devices\": [\n" +
                  "        {\n" +
                  "            \"customerNo\": \"17030002\",\n" +
                  "            \"customerName\": \"7幢1单元1楼附1号\",\n" +
                  "            \"customerAddr\": \"贵州省黔南布依族苗族自治州都1楼附1号\",\n" +
                  "            \"regionName\": \"银湖星城二期\",\n" +
                  "            \"waterUsage\": \"民用\",\n" +
                  "            \"meterCode\": \"00000160568567\",\n" +
                  "            \"meterName\": \"楼附1号\",\n" +
                  "            \"hasValve\": \"1\",\n" +
                  "            \"valveStatus\": \"0\",\n" +
                  "            \"status\": null,\n" +
                  "            \"installTime\": \"2017-01-08\",\n" +
                  "            \"installAddr\": \"贵州省黔南布依族苗族自治州都匀市银湖星城7幢1单元1楼附1号\",\n" +
                  "            \"cusomerMemo\": \"\",\n" +
                  "            \"mechanicalcode\": null\n" +
                  "        },\n" +
                  "        {\n" +
                  "            \"customerNo\": \"17030001\",\n" +
                  "            \"customerName\": \"7幢1单元1楼附2号\",\n" +
                  "            \"customerAddr\": \"贵州省黔南布依族苗族自治州都匀市银湖星城7幢1单元1楼附2号\",\n" +
                  "            \"regionName\": \"银湖星城二期\",\n" +
                  "            \"waterUsage\": \"民用\",\n" +
                  "            \"meterCode\": \"000001\",\n" +
                  "            \"meterName\": \"7幢1单号\",\n" +
                  "            \"hasValve\": \"1\",\n" +
                  "            \"valveStatus\": \"0\",\n" +
                  "            \"status\": null,\n" +
                  "            \"installTime\": \"2017-01-08\",\n" +
                  "            \"installAddr\": \"贵州省黔南布依族苗族自治州都匀市银湖星城7幢1单元1楼附2号\",\n" +
                  "            \"cusomerMemo\": \"\",\n" +
                  "            \"mechanicalcode\": null\n" +
                  "        }\n" +
                  "    ],\n" +
                  "    \"totalCount\": 2\n" +
                  "}";
          DeviceInfoRespone  deviceInfoRespone=JSON.parseObject(userInfo, DeviceInfoRespone.class);
          if(deviceInfoRespone!=null)
          {
              System.out.println("获取设备信息:" + deviceInfoRespone.getTotalCount() + "条");
              if(Integer.parseInt(deviceInfoRespone.getTotalCount())>0) {

                  try {
                      User user = new User();
                      for (DeviceInfo de : deviceInfoRespone.getDevices()) {
                          user.setUserCustomerno(de.getCustomerNo());
                          user.setUserCustomername(de.getCustomerName());
                          user.setUserCustomeraddr(de.getCustomerAddr());
                          user.setUserWaterusage(de.getWaterUsage());
                          user.setUserReginName(de.getRegionName());
                          user.setUserMetercode(de.getMeterCode());
                          user.setUserMetername(de.getMeterName());
                          user.setUserHasvalve(de.getHasValve());
                          user.setUserValuestatus(de.getValveStatus());
                          user.setUserStatus(de.getStatus());
                          user.setUserInstalltime(de.getInstallTime());
                          user.setUserInstalladdr(de.getInstallAddr());
                          user.setUserCusomerMemo(de.getCusomerMemo());
                          user.setUserMechanicalcode(de.getMechanicalCode());
                          user.setUserImporttime(simpleDateFormat.format(calendar.getTime()));//导入时间

                          if (this.insertUserInfo(user) > 0) {
                              System.out.println("插入成功第" + (++num) + "用户");
                          }
//                          if(this.updateUserInfoByCustomerNo(user)>0)
//                          {
//                              System.out.println("更新成功" + (++num) +"条");
//                          }

                      }

                      transactionManager.commit(status);
                  }
                  catch (Exception e)
                  {
                      transactionManager.rollback(status);
                      System.out.println("出现错误:"+e.getMessage());
                  }


              }

          }
          return num;
    }

    /**
     * 添加用户
     * @return
     */
    public boolean addUserInfo()
    {
        //获取数据库全部用户数据
         List<User> userList=selectUserInfo();
        //获取远程用户数据
         String userInfos=UserAndReadingUtil.getCustomerInfo();
         DeviceInfoRespone deviceInfoRespone=JSON.parseObject(userInfos,DeviceInfoRespone.class);
         logger.info("数据库用户:"+userList.size());
         logger.info("远程设备信息:"+deviceInfoRespone.getTotalCount());
        //求差集
         List<DeviceInfo> list=  ListClass.differenceSet(deviceInfoRespone.getDevices(),userList);
         logger.info("求差集所获长度"+list.size());
         List<DeviceInfo> list1= ListClass.intersectionSet(deviceInfoRespone.getDevices(),userList);
        logger.info("求交集所获长度"+list1.size());

        DefaultTransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        transactionDefinition.setName("Transaction-name-addUser");
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status=transactionManager.getTransaction(transactionDefinition);

        try {
            //差集进行增加
            if (list != null && list.size() > 0) {
                try {
                    int num = 0;
                    User user = new User();
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        DeviceInfo de = (DeviceInfo) iterator.next();
                        user.setUserCustomerno(de.getCustomerNo());
                        user.setUserCustomername(de.getCustomerName());
                        user.setUserCustomeraddr(de.getCustomerAddr());
                        user.setUserWaterusage(de.getWaterUsage());
                        user.setUserReginName(de.getRegionName());
                        user.setUserMetercode(de.getMeterCode());
                        user.setUserMetername(de.getMeterName());
                        user.setUserHasvalve(de.getHasValve());
                        user.setUserValuestatus(de.getValveStatus());
                        user.setUserStatus(de.getStatus());
                        user.setUserInstalltime(de.getInstallTime());
                        user.setUserInstalladdr(de.getInstallAddr());
                        user.setUserCusomerMemo(de.getCusomerMemo());
                        user.setUserMechanicalcode(de.getMechanicalCode());
                        user.setUserImporttime(simpleDateFormat.format(calendar.getTime()));//导入时间

                        if (this.insertUserInfo(user) > 0) {
                            logger.info("插入成功第" + (++num) + "用户,用户编号:"+user.getUserCustomerno()+",水表编号:"+user.getUserMetercode()+",阀门状态:"+user.getUserValuestatus());
                        }


                    }
                } catch (Exception e) {
                    logger.info("插入出现错误:" + e.getMessage());
                   throw new Exception("插入异常");
                }

            }
            //交集进行更新
            if (list1 != null && list1.size() > 0) {
                try {
                    int num = 0;
                    User user = new User();
                    Iterator iterator = list1.iterator();
                    while (iterator.hasNext()) {
                        DeviceInfo de = (DeviceInfo) iterator.next();
                        user.setUserCustomerno(de.getCustomerNo());
                        user.setUserCustomername(de.getCustomerName());
                        user.setUserCustomeraddr(de.getCustomerAddr());
                        user.setUserWaterusage(de.getWaterUsage());
                        user.setUserReginName(de.getRegionName());
                        user.setUserMetercode(de.getMeterCode());
                        user.setUserMetername(de.getMeterName());
                        user.setUserHasvalve(de.getHasValve());
                        user.setUserValuestatus(de.getValveStatus());
                        user.setUserStatus(de.getStatus());
                        user.setUserInstalltime(de.getInstallTime());
                        user.setUserInstalladdr(de.getInstallAddr());
                        user.setUserCusomerMemo(de.getCusomerMemo());
                        user.setUserMechanicalcode(de.getMechanicalCode());
                        user.setUserModifiertime(simpleDateFormat.format(calendar.getTime()));//修改时间

                        if (this.updateUserInfoByCustomerNo(user) > 0) {
                            logger.info("更新成功" + (++num) + "条");
                        }
                    }
                } catch (Exception e) {
                    logger.info("更新出现错误:" + e.getMessage());
                    throw new Exception("更新异常");
                }


            }

            transactionManager.commit(status);
        }catch (Exception e)
        {
            logger.info(e.getMessage());
            transactionManager.rollback(status);
            return false;
        }

       return true;
    }
}
