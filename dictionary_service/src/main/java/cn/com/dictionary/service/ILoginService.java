package cn.com.dictionary.service;

import cn.com.dictionary.common.ApiResult;
import cn.com.dictionary.mapper.pojo.UserPojo;

/**
 * @author gejj
 * @package cn.com.dictionary.service
 * @data 2023/4/20 16:05
 */
public interface ILoginService {
    /**
     * 验证用户登录
     * @param username
     * @param password
     * @return
     */
    ApiResult<UserPojo> authenticate(String username, String password);
}
