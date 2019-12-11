package com.ningbo.duyun.util;

import com.ningbo.duyun.bean.DeviceInfo;
import com.ningbo.duyun.bean.User;

import java.util.*;

public class ListClass {
    /**
     * 差集：删除左边集合中在右边集合存在的元素并返回
     * @param left
     * @param right
     * @return
     */
    public static List<DeviceInfo> differenceSet(List<DeviceInfo> left,List<User> right){
        if (left == null){
            return null;
        }
        if (right == null){
            return left;
        }
        //使用LinkedList方便插入和删除
        List<DeviceInfo> res = new LinkedList<>(left);
        Set<String> set = new HashSet<>();
        //设备信息比用户信息多
        if (left.size() >=right.size()) {
            for (User item : right) {
                set.add(item.getUserCustomerno());
            }
            //迭代器遍历listA
            Iterator<DeviceInfo> iter = res.iterator();
            while (iter.hasNext()) {
                DeviceInfo item = iter.next();
                //如果set中包含id则remove
                if (set.contains(item.getCustomerNo())) {
                    iter.remove();
                }
            }
        }

        return res;
    }

    public static List<DeviceInfo> intersectionSet(List<DeviceInfo> left, List<User> right){
        if (left == null){
            return null;
        }
        if (right == null){
            return left;
        }
        //使用LinkedList方便插入和删除
        List<DeviceInfo> res = new LinkedList<>(left);
        List<DeviceInfo> intersectionSet=new LinkedList<>();
        Set<String> set = new HashSet<>();
        //设备信息比用户信息多
        if (left.size() >=right.size()) {
            for (User item : right) {
                set.add(item.getUserCustomerno());
            }
            //迭代器遍历listA
            Iterator<DeviceInfo> iter = res.iterator();
            while (iter.hasNext()) {
                DeviceInfo item = iter.next();
                //如果set中包含id则remove
                if (set.contains(item.getCustomerNo())) {
                    intersectionSet.add(item);
                }
            }
        }

        return intersectionSet;
    }


}
