package cn.com.dictionary.realm;

import cn.com.dictionary.dao.mapper.RoleMenuMapper;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserRoleService;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.config
 * @data 2023/4/25 17:11
 */
public class Realm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(Realm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("Start with shiro authorization");
        String username = (String)principals.getPrimaryPrincipal();
        User user = userService.query(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roles = userRoleService.queryByRoleId(user.getRoleId());

        logger.info("Shiro authorization successful");
        return info;
    }

    /**
     * 身份认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("identity authentication");
        UsernamePasswordToken usernameToken = (UsernamePasswordToken) token;
        String username = usernameToken.getUsername();
        String password = String.valueOf(usernameToken.getPassword());
        User user = userService.query(username);
        if(user == null){
            logger.info("用户不存在！");
            throw new UnknownAccountException("账号或密码不正确！");
        }

        if(!user.getPassword().equals(password)){
            logger.info("密码错误！");
            throw new IncorrectCredentialsException("账号或密码不正确！");
        }

        if(!user.isStatus()){
            throw new RuntimeException("用户状态异常！");
        }

        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("username", username);
        subject.getSession().setAttribute("id", user.getId());
        logger.info("Shiro identity authentication successful");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected void doClearCache(PrincipalCollection principals) {
        super.doClearCache(principals);
    }
}
