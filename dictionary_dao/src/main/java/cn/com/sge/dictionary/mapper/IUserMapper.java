package cn.com.sge.dictionary.mapper;

import cn.com.sge.dictionary.mapper.pojo.UserPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gejj
 * @package cn.com.sge.dictionary.mapper
 * @data 2023/4/17 17:29
 */
@Mapper
public interface IUserMapper {
    List<UserPojo> getAllUser();
}
