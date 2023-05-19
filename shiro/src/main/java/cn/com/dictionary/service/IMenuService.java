package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.Permission;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/19 14:28
 */
public interface IMenuService {

    List<Permission> queryByUserId(String id);
}
