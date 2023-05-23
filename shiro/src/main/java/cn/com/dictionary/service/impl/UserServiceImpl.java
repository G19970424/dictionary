package cn.com.dictionary.service.impl;

import cn.com.dictionary.common.GlobalException;
import cn.com.dictionary.common.enumdata.EncryptionEnum;
import cn.com.dictionary.common.utils.SaltUtil;
import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.Role;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:25
 */
@Service
@Transactional
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
    public Set<Role> queryUserRoles(String id) {
        Set<Role> roles = userMapper.queryUserRoles(id);
        for (Role role : roles) {
            logger.info(role.toString());
        }
        return roles;
    }

    @Override
    public Set<Permission> queryUserPermission(String id) {
        Set<Permission> permissions = userMapper.queryUserPermission(id);
        for (Permission permission : permissions) {
            logger.info(permission.toString());
        }
        return permissions;
    }

    @Override
    public void register(User user) {
        //获取密码
        String password = user.getPassword();
        //生成随机盐
        String salt = SaltUtil.getSalt();
        ByteSource bytes = ByteSource.Util.bytes(salt);
        //加密后密码
        String newPass = new SimpleHash(EncryptionEnum.MD5.getValue(), password, salt, 8).toHex();
        user.setSalt(salt);
        user.setPassword(newPass);
        User oldUser = userMapper.queryUserByName(user.getUsername());
        if(oldUser != null){
            throw new GlobalException("用户名已存在，请重新选择用户名！", 400);
        }
        userMapper.register(user);
    }

    @Override
    public List<Permission> queryPermByRoleId(String id) {
        return userMapper.queryPermByRoleId(id);
    }

}
