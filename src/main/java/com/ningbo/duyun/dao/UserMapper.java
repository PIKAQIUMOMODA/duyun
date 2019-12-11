package com.ningbo.duyun.dao;

import com.ningbo.duyun.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("UserMapper")
public interface UserMapper {
    /**
     * 插入用户信息
     * @param user
     * @return
     */
    int insertUserInfo(User user);

    /**
     * 通过用户编号更新数据
     * @param user
     * @return
     */
     int updateUserInfoByCustomerNo(User user);

    /**
     * 查询用户信息
     * @return
     */
    List<User> selectUserInfo();

    /**
     * 通过用户编号查询用户信息
     * @param customerNo
     * @return
     */
     User selectUserByCustomerNo(String customerNo);

    /**
     * 通过用户id删除用户信息
     * @param id
     * @return
     */
     int delteUserByCustomerId(int id);

}
