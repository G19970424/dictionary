package cn.com.dictionary.service.impl;

import cn.com.dictionary.common.enumdata.TimeEnum;
import cn.com.dictionary.common.utils.IdGeneratorUtil;
import cn.com.dictionary.dao.mapper.LoginLogMapper;
import cn.com.dictionary.dao.pojo.LoginLog;
import cn.com.dictionary.service.ILoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gejj
 * @data 2023/5/24 14:26
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService {

    private static final Logger logger = LoggerFactory.getLogger(LoginLogServiceImpl.class);

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public int queryWrongLoginLog(String id) {
        return loginLogMapper.queryWrongLoginCount(id, TimeEnum.TEN_MINUTE.getKey());
    }

    @Override
    public void insert(LoginLog loginLog) {
        //生成唯一登录日志id
        loginLog.setId(IdGeneratorUtil.getInstance().getId());
        loginLogMapper.insert(loginLog);
    }
}
