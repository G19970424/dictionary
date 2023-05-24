package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gejj
 * @data 2023/5/22 17:10
 */
@Mapper
public interface UserMapper {

    User queryByName(@Param("username") String username);

    User queryById(@Param("id") String id);

    void insert(User user);
}
