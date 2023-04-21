package cn.com.dictionary.service.impl;

import cn.com.dictionary.common.utils.UserStatusUtil;
import cn.com.dictionary.mapper.pojo.UserPojo;
import cn.com.dictionary.service.ILoginService;
import cn.com.dictionary.utils.enumdata.UserStatusEnum;
import cn.com.dictionary.common.ApiResult;
import cn.com.dictionary.mapper.IUserLogMapper;
import cn.com.dictionary.mapper.IUserMapper;
import cn.com.dictionary.mapper.pojo.UserLogPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/20 16:05
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserLogMapper userLogMapper;

    @Override
    public ApiResult<UserPojo> authenticate(String username, String password) {
        ApiResult<UserPojo> result = new ApiResult<>(false);
        UserPojo user = userMapper.queryByUserName(username);
        UserLogPojo userLog = new UserLogPojo();


        if(user == null || !user.getPassword().equals(password)){
            result.setMsg("用户名或密码错误！");
            return result;
        }
        //Check user status
        boolean flag = UserStatusUtil.checkUserStatus(user.getStatus());
        if(!flag){
            result.setMsg("用户异常，请更换用户登录！");
            return result;
        }

        //获取用户10分钟登录失败次数
        int count = userLogMapper.queryNumberOfLogins(user.getId());
        if(count>=3){
            result.setMsg("尝试登录次数超时3次，请稍后重试！");
            return result;
        }
        //更新用户登录日志
        userLogMapper.insertLoginInfo(userLog);
        result.setSuccess(true);
        result.setMsg("登录成功！");
        return result;
    }
}
