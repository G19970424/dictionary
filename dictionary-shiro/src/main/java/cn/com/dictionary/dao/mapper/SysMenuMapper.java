package cn.com.dictionary.dao.mapper;

import cn.com.dictionary.dao.pojo.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.dictionary.dao
 * @data 2023/4/25 17:29
 */
@Repository
public interface SysMenuMapper {
    List<RoleMenu> queryAll();
}
