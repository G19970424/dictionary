package cn.com.dictionary.service.impl;

import cn.com.dictionary.dao.mapper.AuditLogMapper;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.dao.pojo.UserLoginLog;
import cn.com.dictionary.service.IAuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gejj
 * @data 2023/5/19 16:54
 *
 * 主要用于公共审计日志
 */
@Service
@Transactional
public class AuditLogServiceImpl implements IAuditLogService {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);

    @Autowired
    private AuditLogMapper auditLogMapper;
    /**
     * 通过用户id 获取近10分钟登录失败次数
     * @param id 用户id
     * @return 近10分钟内登陆失败次数
     */
    @Override
    public int queryLoginNumber(String id) {
        return auditLogMapper.queryLoginNumber(id);
    }

    /**
     *
     * @param userLog
     */
    @Override
    public void insert(UserLoginLog userLog) {
        auditLogMapper.insert(userLog);
    }
}
