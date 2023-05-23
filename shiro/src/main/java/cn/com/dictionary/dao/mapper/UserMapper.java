package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.Role;
import cn.com.dictionary.dao.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:26
 */
@Mapper
public interface UserMapper extends BaseMapper {
    User queryUserByName(@Param("username") String username);

    Set<Role> queryUserRoles(@Param("id") String id);

    Set<Permission> queryUserPermission(@Param("id") String id);

    void register(User user);

    List<Permission> queryPermByRoleId(@Param("id")String id);

}
