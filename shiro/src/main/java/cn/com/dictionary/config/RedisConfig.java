package cn.com.dictionary.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gejj
 * @data 2023/5/15 15:03
 */
@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * redis 缓存管理器
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager manager = new RedisCacheManager();
        manager.setRedisManager(redisManager());
        return manager;
    }

    /**
     * redis 管理器，用于配置redis信息
     * @return
     */
    @Bean("redisManager")
    public RedisManager redisManager(){
        RedisManager manager = new RedisManager();
        manager.setPort(port);
        manager.setHost(host);
        manager.setTimeout(timeout);
        return manager;
    }




    /**
     * redis session dao层实现 配置
     * @return
     */
    @Bean("sessionDAO")
    public RedisSessionDAO redisSessionDao(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

}
