package cn.com.dictionary.service;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service
 * @data 2023/4/25 17:29
 */
public interface IUserRoleService {
    List<String> queryByRoleId(Integer roleId);
}