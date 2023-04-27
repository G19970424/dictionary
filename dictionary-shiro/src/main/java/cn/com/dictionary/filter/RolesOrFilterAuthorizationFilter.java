package cn.com.dictionary.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Collections;
import java.util.Set;

/**
 * @author gejj
 * @package cn.com.dictionary.filter
 * @data 2023/4/25 17:30
 *
 */
public class RolesOrFilterAuthorizationFilter extends RolesAuthorizationFilter {
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object object){
        Subject subject = this.getSubject(request, response);
        String[] rolesArrays = ((String[]) object);
        if(rolesArrays != null && rolesArrays.length != 0){
            Set<String> set = CollectionUtils.asSet(rolesArrays);
            for (String s : set) {
                if(subject.hasRole(s)){
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }
}
