package cn.com.dictionary.realm;

import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Action;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 13:55
 */
public class AuthorizationRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationRealm.class);

    @Autowired
    private IUserService userService;
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
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();

        userService.queryUserRoles(user.getUsername()).forEach(role->roleSet.add(role.getName()));
        userService.queryUserPermission(user.getUsername()).forEach(permission -> permissionSet.add(permission.getName()));

        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
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
        User user = userService.queryUserByName(username);

        if(user == null){
            throw new IncorrectCredentialsException();
        }

        if(user.getStatus() == -1){
            throw new LockedAccountException();
        }

        if(user.getLoginNumber() > 3){
            throw new ExcessiveAttemptsException();
        }
        String password = user.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),password,getName());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("USER_SESSION", user);
        return info;
    }
}
