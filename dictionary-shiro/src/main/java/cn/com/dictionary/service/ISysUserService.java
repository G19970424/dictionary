package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.User;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.service
 * @data 2023/4/25 17:28
 */
public interface ISysUserService {

    List<User> queryAll();
}
