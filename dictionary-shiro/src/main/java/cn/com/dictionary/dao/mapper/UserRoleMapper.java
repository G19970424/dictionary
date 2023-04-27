package cn.com.dictionary.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.dao.mapper
 * @data 2023/4/27 16:05
 */
@Mapper
public interface UserRoleMapper {

    List<String> queryByRoleId(Integer roleId);
}
