package cn.com.dictionary.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gejj
 * @package cn.com.dictionary.filter
 * @data 2023/4/25 17:31
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
    //踢出后的地址
    private String kickoutUrl;
    private boolean kickoutAfter = false;
    private int maxSession = 1;
    private SessionManager sessionManager;
    private Cache<String , Deque<Serializable>> cache;


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if(!subject.isAuthenticated() && !subject.isRemembered()){
            return true;
        }

        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();

        Serializable id = session.getId();
        Deque<Serializable> deque = cache.get(username);

        if(deque == null){
            deque = new LinkedList<Serializable>();
        }

        if(!deque.contains(id) && session.getAttribute("kickout") == null){
            deque.push(id);
            cache.put(username,deque);
        }

        while (deque.size() > maxSession){
            Serializable kickoutSessionId = null;
            if(kickoutAfter){
                kickoutSessionId = deque.removeFirst();
                cache.put(username, deque);
            }else{
                kickoutSessionId = deque.removeLast();
                cache.put(username, deque);
            }
        }
        return false;
    }


    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public boolean isKickoutAfter() {
        return kickoutAfter;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Cache<String, Deque<Serializable>> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Deque<Serializable>> cache) {
        this.cache = cache;
    }

    public void setCacheManager(CacheManager cacheManager){
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

}
