package cn.com.dictionary;

import cn.com.dictionary.Application;
import cn.com.dictionary.mapper.IUserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author gejj
 * @package cn.com.dictionary.test
 * @data 2023/4/17 17:37
 */

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class DataSourceTest {
    @Autowired
    private IUserMapper userMapper;

    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    public void dataSource() throws SQLException {
        dataSource.getConnection();
    }


    @Test
    public void testDao(){
        userMapper.queryAll();
    }
}
