package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.UserRoleMapper;
import cn.com.dictionary.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/25 17:42
 */
@Service("SysUserRoleServiceImpl")
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> queryByRoleId(Integer roleId) {
        List<String> result = userRoleMapper.queryByRoleId(roleId);
        return result;
    }
}
