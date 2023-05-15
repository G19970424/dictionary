package cn.com.dictionary.handler;

import cn.com.dictionary.common.exception.ExcelException;
import cn.com.dictionary.common.result.ApiResult;
import cn.com.dictionary.common.utils.ResultUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gejj
 * @data 2023/5/8 15:41
 * 全局异常处理器
 *
 * 登录异常信息 (AuthenticationException)
 *      IncorrectCredentialsException （密码错误） -- 6001
 *      ExpiredCredentialsException（登录身份过期） -- 6002
 *      LockedAccountException（账号被冻结）        -- 6003
 *      ExcessiveAttemptsException（登录失败过多）  -- 6004
 *      DisabledAccountException（禁用的帐号）
 *      UnknownAccountException（错误的帐号）
 *      UnauthorizedException(权限越界)             --6005
 *      UnauthenticatedException(用户未登录)         -- 6006
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 如果身份验证失败请捕获  或其子类，常见的如：
     * 具体请查看其继承关系；
     * 对于页面的错误消息展示，最好使用如 “用户名 / 密码错误” 而不是 “用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
     */
    //无权限
    @ExceptionHandler(value = UnauthorizedException.class)
    public ApiResult handler(UnauthorizedException e){
        return ResultUtil.FAIL(6005);
    }

    //身份过期
    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public ApiResult handler(ExpiredCredentialsException e){
        return ResultUtil.FAIL(6002);
    }

    //密码错误
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ApiResult handler(IncorrectCredentialsException e){
        return ResultUtil.FAIL(6001);
    }

    //账号冻结
    @ExceptionHandler(value = LockedAccountException.class)
    public ApiResult handler(LockedAccountException e){
        return ResultUtil.FAIL(6003);
    }

    //用户未登录
    @ExceptionHandler(value = UnauthenticatedException.class)
    public ApiResult handler(UnauthenticatedException e){
        return ResultUtil.FAIL(6006);
    }

    //登录次数超过3次
    @ExceptionHandler(value = ExcessiveAttemptsException.class)
    public ApiResult handler(ExcessiveAttemptsException e){
        return ResultUtil.FAIL(6004);
    }

    //Excel util Exception
    @ExceptionHandler(value = ExcelException.class)
    public ApiResult handler(ExcelException e){
        return ResultUtil.FAIL(e.getMessage());
    }
}
