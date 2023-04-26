package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/20 16:05
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private IUserLogMapper userLogMapper;

    @Override
    public User authenticate(String username, String password) {
//        if(user == null || !user.getPassword().equals(password)){
//            result.setMsg("用户名或密码错误！");
//            return result;
//        }
//        //Check user status
//        boolean flag = UserStatusUtil.checkUserStatus(user.getStatus());
//        if(!flag){
//            result.setMsg("用户异常，请更换用户登录！");
//            return result;
//        }
//
//        //获取用户10分钟登录失败次数
//        int count = userLogMapper.queryNumberOfLogins(user.getId());
//        if(count>=3){
//            result.setMsg("尝试登录次数超时3次，请稍后重试！");
//            return result;
//        }
        //更新用户登录日志
//        userLogMapper.insertLoginInfo(userLog);

        return null;
    }
}
