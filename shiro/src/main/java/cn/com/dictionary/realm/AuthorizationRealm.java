package cn.com.dictionary.realm;

import cn.com.dictionary.common.utils.DateUtil;
import cn.com.dictionary.common.utils.IdGeneratorUtil;
import cn.com.dictionary.dao.mapper.UserMapper;
import cn.com.dictionary.dao.pojo.LoginLog;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.ILoginLogService;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/15 13:55
 */
public class AuthorizationRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationRealm.class);

    @Lazy
    @Autowired
    private IUserService userService;

    @Lazy
    @Autowired
    private ILoginLogService loginLogService;
    /**
     * 用户授权
     *      1.验证用户权限。
     *      2.添加角色与角色权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("用户授权");
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        User user =(User) session.getAttribute("USER_SESSION");
//        if(user != null){
//            //获取用户角色
//            loginService.queryUserRoles(user.getId()).forEach(role->{
//                info.addRole(role.getName());
//                List<Permission> perm = loginService.queryPermByRoleId(role.getId());
//                if(!perm.isEmpty()){
//                    perm.forEach(p ->{info.addStringPermission(p.getName());});
//                }
//            });
//            return info;
//        }
        return null;
    }

    /**
     * 用户认证
     *      1.用于验证用户登录信息
     *      2.用户验证成功，将用户信息存入redis中
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("用户认证");
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userService.queryByName(username);

        //判断用户是否存在
        if(user == null){
            throw new UnknownAccountException();
        }
        String uid = user.getId();
        //根据用户id 获取近10分钟登录失败次数
        int count = loginLogService.queryWrongLoginLog(uid);

        //用户10分钟内登录
        if(count >= 3){
            throw new ExcessiveAttemptsException();
        }

        //判断用户是否被冻结
        if(!user.isStatus()){
            throw new LockedAccountException();
        }

        //用户被锁定
        if(user.isLock()){
            throw new DisabledAccountException();
        }
        //记录登录日志
        LoginLog log = new LoginLog();
        log.setUserId(uid);
        String md5 = new SimpleHash("MD5", password, user.getSalt(), 8).toHex();
        //验证登录密码
        if(!user.getPassword().equals(md5)){
            log.setStatus(false);
            loginLogService.insert(log);
            throw new IncorrectCredentialsException();
        }

        //登录成功
        String dbPassword = user.getPassword();
        ByteSource sourceSalt = ByteSource.Util.bytes(user.getSalt());
        //保存用户信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,dbPassword,sourceSalt,getName());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("USER_SESSION", user);

        //记录用户登录信息
        log.setStatus(true);
        loginLogService.insert(log);
        return info;
    }
}
