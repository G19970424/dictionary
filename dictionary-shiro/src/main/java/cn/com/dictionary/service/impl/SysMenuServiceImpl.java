package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.SysMenuMapper;
import cn.com.dictionary.dao.pojo.RoleMenu;
import cn.com.dictionary.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/25 17:41
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<RoleMenu> queryAll() {
        List<RoleMenu> result = sysMenuMapper.queryAll();
        return result;
    }
}
