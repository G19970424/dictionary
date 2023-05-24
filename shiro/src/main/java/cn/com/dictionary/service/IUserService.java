package cn.com.dictionary.service;

import cn.com.dictionary.common.result.ApiResult;
import cn.com.dictionary.dao.pojo.User;

/**
 * @author gejj
 * @data 2023/5/22 17:01
 */
public interface IUserService {
    User queryByName(String userName);

    User queryById(String id);

    void register(User user);
}
