package cn.com.dictionary.controller;

import cn.com.dictionary.common.result.ApiResult;
import cn.com.dictionary.common.utils.ResultUtil;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gejj
 * @data 2023/5/16 11:17
 */
@Api(value = "Login|登录控制器")
@RestController
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录接口",notes = "提供账号和密码")
    @PostMapping(value = "/login",consumes = "application/json")
    public ApiResult login(@ApiParam(name="user",value = "用户登录",required = true)@RequestBody User user){
        logger.info("用户登录：{}",user.getUsername());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> map = new HashMap<>();
        subject.login(token);
        map.put("token", subject.getSession().getId());
        return ResultUtil.SUCCESS("登录成功！",200);
    }

    @PostMapping(value = "/register")
    public ApiResult register(@ApiParam(name = "user",value="用户注册",required = true) @RequestBody User user){
        userService.register(user);
        return ResultUtil.SUCCESS("注册成功！",200);
    }

}
