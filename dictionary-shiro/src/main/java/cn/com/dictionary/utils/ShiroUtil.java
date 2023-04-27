package cn.com.dictionary.utils;

import cn.com.dictionary.dao.pojo.RoleMenu;
import cn.com.dictionary.service.IMenuService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gejj
 * @package cn.com.dictionary.utils
 * @data 2023/4/25 17:36
 */
public class ShiroUtil {
    public static Map<String, String> getFilterChainDefinitionMap(IMenuService sysMenuService){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<RoleMenu> roleMenus = sysMenuService.queryAll();

//        List<SysMenuFilterVO> list = sysMenuService.selectAll();
////        //游客，开发权限
////        filterChainDefinitionMap.put("/guest/**", "anon");
////        //用户，需要角色权限 “user”
////        filterChainDefinitionMap.put("/user/**", "roles[user]");
////        //管理员，需要角色权限 “admin”
////        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
////        //其余接口一律拦截
//        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//        for(SysMenuFilterVO filter : list){
//            filterChainDefinitionMap.put(filter.getUrl()+"/**","roleOrFilter["+(filter.getRoles()==null?"":filter.getRoles())+"]");
//        }
        filterChainDefinitionMap.put("/**", "kickout,anon");
        return filterChainDefinitionMap;
    }
}
