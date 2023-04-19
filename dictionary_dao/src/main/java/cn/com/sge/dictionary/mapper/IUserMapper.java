package cn.com.sge.dictionary.mapper;

import cn.com.sge.dictionary.mapper.pojo.UserPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.sge.dictionary.mapper
 * @data 2023/4/17 17:29
 */
@Mapper
public interface IUserMapper {
    /**
     * 获取所有用户
     * @return
     */
    List<UserPojo> queryAll();

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserPojo queryByUserName(@Param("username") String username);


    /**
     * 通过登录名获取用户密码
     * @param username
     * @return
     */
    String queryByLoginName(@Param("username") String username);

    /**
     * 新增用户
     * @param userPojo
     * @return
     */
    void insert(UserPojo userPojo);

    /**
     * 修改用户
     * @param userPojo
     */
    void update(UserPojo userPojo);

    /**
     * 修改用户密码
     * @param password
     */
    void updatePassword(@Param("username") String username,@Param("password") String password);

    /**
     * 修改用户状态
     * @param status
     */
    void updateUserStatue(@Param("username") String username,@Param("password")String password,@Param("status") String status);

    /**
     * 注销用户
     * @param username
     * @param password
     */
    void logout(@Param("username") String username,@Param("password") String password);
}
