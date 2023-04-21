package cn.com.dictionary.mapper;

import cn.com.dictionary.mapper.pojo.UserLogPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.mapper
 * @data 2023/4/20 14:51
 */
@Mapper
public interface IUserLogMapper {

    /**
     * 获取用户近三分钟登录次数
     * @param userId
     * @return
     */
    int queryNumberOfLogins(@Param("userId") String userId);

    /**
     * 获取用户登录信息
     * @param userId
     */
    List<UserLogPojo> queryAll(@Param("userId") String userId);

    /**
     * 更新用户登录信息
     * @param UserLogPojo
     */
    void insertLoginInfo(@Param("UserLogPojo") UserLogPojo UserLogPojo);
}
