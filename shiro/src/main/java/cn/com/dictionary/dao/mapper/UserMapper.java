package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.Role;
import cn.com.dictionary.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:26
 */
@Mapper
public interface UserMapper {
    User queryUserByName(@Param("username") String username);

    Set<Role> queryUserRoles(@Param("username") String username);

    Set<Permission> queryUserPermission(@Param("username") String username);

    void register(@Param("user") User user);
}
