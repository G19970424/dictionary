package cn.com.dictionary.service;

import cn.com.dictionary.dao.pojo.LoginLog;

/**
 * @author gejj
 * @data 2023/5/24 14:26
 */
public interface ILoginLogService {
    int queryWrongLoginLog(String id);

    void insert(LoginLog loginLog);
}
