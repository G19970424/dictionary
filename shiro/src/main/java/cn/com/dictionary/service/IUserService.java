package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.Role;
import cn.com.dictionary.dao.pojo.User;

import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:25
 */
public interface IUserService {
    User queryUserByName(String username);

    Set<Role> queryUserRoles(String username);

    Set<Permission> queryUserPermission(String username);
}
