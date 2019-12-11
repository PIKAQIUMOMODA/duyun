package com.ningbo.duyun.dao;

import com.ningbo.duyun.bean.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LogMapper {

    @Insert("insert into log (log_type,log_content,log_createtime) VALUES (#{logType},#{logContent},#{logCreatetime})")
    public int insertLog(Log log);

    @Select("select * from log where log_type=#{type}")
    public List<Log> selectByType(@Param("type") int type);
}
