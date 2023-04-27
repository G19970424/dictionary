package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.dao
 * @data 2023/4/25 17:29
 */
@Mapper
public interface RoleMenuMapper {
    List<RoleMenu> queryAll();
}
