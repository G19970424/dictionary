package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/19 14:28
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Override
    public List<Permission> queryByUserId(String id) {
        return null;
    }
}
