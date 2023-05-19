package cn.com.dictionary.config;

import cn.com.dictionary.realm.AuthorizationRealm;
import cn.com.dictionary.session.AuthorizationSessionManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gejj
 * @data 2023/5/15 13:55
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Autowired
    @Qualifier("redisManager")
    private RedisManager redisManager;

    @Autowired
    @Qualifier("sessionDAO")
    private SessionDAO sessionDAO;

    @Autowired
    @Qualifier("redisCacheManager")
    private RedisCacheManager redisCacheManager;
    /**
     * shiro 拦截器，实现权限相关拦截
     * 常用过滤器
     *      anon：无需认证即可访问。
     *      authc：必须认证才可访问
     *      user：如果使用rememberMe的功能可以访问
     *      perms：该资源必须得到资源权限才可访问
     *      role：该资源必须得到角色授权才可访问
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        logger.info("======shiro filter stater=======");
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //权限控制map
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //设置未登录界面
        shiroFilter.setLoginUrl("/user/login.html");
        //登录成功跳转页面
        shiroFilter.setSuccessUrl("/user/main.html");
        shiroFilter.setFilters(filterMap);
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        logger.info("======shiro set success======");
        return shiroFilter;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("sessionManager") SessionManager sessionManager,@Qualifier("realm") AuthorizingRealm realm){
        DefaultWebSecurityManager defaultWebSecurity = new DefaultWebSecurityManager();
        //自定义登录、授权
        defaultWebSecurity.setRealm(realm);
        //自定义session管理器
        defaultWebSecurity.setSessionManager(sessionManager);
        //自定义缓存管理器
        defaultWebSecurity.setCacheManager(redisCacheManager);
        ThreadContext.bind(defaultWebSecurity);
        return defaultWebSecurity;
    }

    /**
     * session 管理器，主要使用redis
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        AuthorizationSessionManager sessionManager = new AuthorizationSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        return sessionManager;
    }
    /**
     * 自定义登录、授权
     * @return
     */
    @Bean("realm")
    public AuthorizingRealm realm(@Qualifier("hashedCredentialsMatcher") CredentialsMatcher hashedCredentialsMatcher){
        AuthorizationRealm realm = new AuthorizationRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }


    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");//定义加密算法
        matcher.setHashIterations(8);//定义散列次数
        return matcher;
    }

    /**
     * shiro 生命周期处理器
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}
