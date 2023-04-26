package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.dao
 * @data 2023/4/25 16:13
 */
@Mapper
public interface UserMapper {
    /**
     * 获取所有用户
     * @return
     */
    List<User> queryAll();

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    User queryByUserName(@Param("username") String username);


    /**
     * 通过登录名获取用户密码
     * @param username
     * @return
     */
    String queryByLoginName(@Param("username") String username);

    /**
     * 新增用户
     * @param user
     * @return
     */
    void insert(User user);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 修改用户密码
     * @param password
     */
    void updatePassword(@Param("username") String username, @Param("password") String password);

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
