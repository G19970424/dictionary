package cn.com.dictionary.service.impl;

import cn.com.dictionary.common.exception.RegisterException;
import cn.com.dictionary.common.utils.IdGeneratorUtil;
import cn.com.dictionary.common.utils.SaltUtil;
import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gejj
 * @data 2023/5/24 13:49
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByName(String userName) {
        return userMapper.queryByName(userName);
    }

    @Override
    public User queryById(String id) {
        return userMapper.queryById(id);
    }

    /**
     * 用于用户自行注册，注册用户需要内部用户审核，方可进行登录使用
     * @param user
     */
    @Override
    public void register(User user) {
        //判断用户是否存在 存在则注册失败
        User oldUser = userMapper.queryByName(user.getUsername());
        if(oldUser != null){
            logger.error("[Register Error] {} 用户存在，注册失败！",user.getUsername());
            throw new RegisterException("用户名已存在，请修改用户名！");
        }
        //生成用户唯一id
        String id = IdGeneratorUtil.getInstance().getId();
        oldUser = userMapper.queryById(id);
        if(oldUser != null){
            logger.error("[Register Error] id：{} 生成失败 ！",id);
            throw new RegisterException("注册失败！");
        }
        user.setId(id);
        //生成随机盐
        String salt = SaltUtil.getSalt();
        user.setSalt(salt);
        //生成加密密码
        String newPassword = new SimpleHash("MD5", "admin", salt, 8).toHex();
        user.setPassword(newPassword);
        //由于改注册接口通过外部注册，需要内部用户审核通过，赋予权限。
        user.setStatus(false);
        user.setLock(false);
        userMapper.insert(user);
    }
}
