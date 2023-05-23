package cn.com.dictionary.handler;

import cn.com.dictionary.common.GlobalException;
import cn.com.dictionary.common.exception.ExcelException;
import cn.com.dictionary.common.result.ApiResult;
import cn.com.dictionary.common.utils.ResultUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = UnauthorizedException.class)
    public ApiResult handler(UnauthorizedException e){
        logger.error("Unauthorized Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6005);
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    public ApiResult handler(UnknownAccountException e){
        logger.error("Unknown Account Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6005);
    }

    //身份过期
    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public ApiResult handler(ExpiredCredentialsException e){
        logger.error("ExpiredCredential Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6002);
    }

    //密码错误
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ApiResult handler(IncorrectCredentialsException e){
        logger.error("IncorrectCredential Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6001);
    }

    //账号冻结
    @ExceptionHandler(value = LockedAccountException.class)
    public ApiResult handler(LockedAccountException e){
        logger.error("LockedAccount Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6003);
    }

    //用户未登录
    @ExceptionHandler(value = UnauthenticatedException.class)
    public ApiResult handler(UnauthenticatedException e){
        logger.error("Unauthenticated Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6006);
    }

    //登录次数超过3次
    @ExceptionHandler(value = ExcessiveAttemptsException.class)
    public ApiResult handler(ExcessiveAttemptsException e){
        logger.error("ExcessiveAttempts Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(6004);
    }

    //Excel util Exception
    @ExceptionHandler(value = ExcelException.class)
    public ApiResult handler(ExcelException e){
        logger.error("Excel Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(e.getMessage());
    }

    @ExceptionHandler(value = GlobalException.class)
    public ApiResult handler(GlobalException e){
        logger.error("Global Exception:{}" ,e.getMessage());
        return ResultUtil.FAIL(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResult handler(Exception e){
        logger.error(e.getMessage());
        return ResultUtil.FAIL(500);
    }
}
