package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.Role;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:25
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        User user = userMapper.queryUserByName(username);
        return user;
    }

    @Override
    public Set<Role> queryUserRoles(String username) {
        Set<Role> roles = userMapper.queryUserRoles(username);
        return roles;
    }

    @Override
    public Set<Permission> queryUserPermission(String username) {
        Set<Permission> permissions = userMapper.queryUserPermission(username);
        return null;
    }
}
