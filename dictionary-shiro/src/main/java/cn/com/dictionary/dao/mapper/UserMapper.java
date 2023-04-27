package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.dao.mapper
 * @data 2023/4/27 16:05
 */
@Mapper
public interface UserMapper {

    List<User> queryAll();

    User query(@Param("username") String username);
}
