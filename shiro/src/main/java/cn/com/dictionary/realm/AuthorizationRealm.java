package cn.com.dictionary.realm;

import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.dao.pojo.UserLoginLog;
import cn.com.dictionary.service.IAuditLogService;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/15 13:55
 */
public class AuthorizationRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuditLogService auditLogService;

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
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user =(User) session.getAttribute("USER_SESSION");
        if(user != null){
            //获取用户角色
            userService.queryUserRoles(user.getId()).forEach(role->{
                info.addRole(role.getName());
                List<Permission> perm = userService.queryPermByRoleId(role.getId());
                if(!perm.isEmpty()){
                    perm.forEach(p ->{info.addStringPermission(p.getName());});
                }
            });
            return info;
        }
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
        User user = userService.queryUserByName(username);

        //判断用户是否存在
        if(user == null){
            throw new UnknownAccountException();
        }

        //记录登录日志
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(user.getId());

        //根据用户id 获取近10分钟登录失败次数

        int loginNumber = auditLogService.queryLoginNumber(user.getId());
        //判断用户十分钟内登陆失败次数
        //十分钟内 登录失败次数 大于等于3 限制登录
        if(loginNumber >= 3){
            throw new ExcessiveAttemptsException();
        }

        //判断用户是否被冻结
        if(user.getStatus() == -1){
            throw new LockedAccountException();
        }

        //判断用户密码
        if(!password.equals(user.getPassword())){
            userLoginLog.setStatus(false);
            auditLogService.insert(userLoginLog);
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
        userLoginLog.setStatus(true);
        auditLogService.insert(userLoginLog);
        return info;
    }
}
