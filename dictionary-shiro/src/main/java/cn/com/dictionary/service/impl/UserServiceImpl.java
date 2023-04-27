package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/25 17:28
 */
@Service("SysUserServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        List<User> result = userMapper.queryAll();
        return result;
    }

    @Override
    public User query(String username) {
        User result = userMapper.query(username);
        return result;
    }
}
