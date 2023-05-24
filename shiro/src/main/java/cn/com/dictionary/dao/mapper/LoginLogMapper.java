package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/24 14:14
 */
@Mapper
public interface LoginLogMapper {
    List<LoginLog> queryById(@Param("id") String id);

    int queryWrongLoginCount(@Param("id") String id ,@Param("time") String time);

    void insert(LoginLog loginLog);
}
