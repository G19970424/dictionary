package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.dao.pojo.UserLoginLog;

/**
 * @author gejj
 * @data 2023/5/19 16:54
 */
public interface IAuditLogService {
    int queryLoginNumber(String id);

    void insert(UserLoginLog userLoginLog);
}
