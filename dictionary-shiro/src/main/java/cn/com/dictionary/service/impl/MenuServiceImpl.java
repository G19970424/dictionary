package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.RoleMenuMapper;
import cn.com.dictionary.dao.pojo.RoleMenu;
import cn.com.dictionary.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/25 17:41
 */
@Service("SysMenuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenu> queryAll() {
        List<RoleMenu> result = roleMenuMapper.queryAll();
        return result;
    }
}
