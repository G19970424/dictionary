package cn.com.dictionary.test;

import cn.com.sge.dictionary.SpringApplication;
import cn.com.sge.dictionary.mapper.IUserMapper;
import cn.com.sge.dictionary.mapper.pojo.UserPojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author gejj
 * @package cn.com.dictionary.test
 * @data 2023/4/17 17:37
 */

@SpringBootTest(classes = {SpringApplication.class})
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

        List<UserPojo> allUser = userMapper.getAllUser();
        for (UserPojo pojo : allUser) {
            System.out.println(pojo.toString());
        }
    }
}
