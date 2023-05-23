package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gejj
 * @data 2023/5/19 17:09
 */
@Mapper
public interface AuditLogMapper {
    int queryLoginNumber(String id);

    void insert(UserLoginLog userLog);
}
