package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.RoleMenu;
import cn.com.dictionary.dao.pojo.User;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service
 * @data 2023/4/25 17:41
 */
public interface ISysMenuService {
    /**
     * 用户-菜单关系；
     * @return
     */
    List<RoleMenu> queryAll();
}
