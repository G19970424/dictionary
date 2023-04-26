package cn.com.dictionary.realm;

import cn.com.dictionary.dao.mapper.SysMenuMapper;
import cn.com.dictionary.service.ISysUserRoleService;
import cn.com.dictionary.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gejj
 * @package cn.com.dictionary.config
 * @data 2023/4/25 17:11
 */
public class Realm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(Realm.class);

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
