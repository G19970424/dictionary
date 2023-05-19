package cn.com.test;

import cn.com.dictionary.Application;
import cn.com.dictionary.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author gejj
 * @data 2023/5/16 9:21
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class DriverTest {

    @Autowired
    DataSource dataSource;


    @Test
    public void testDriver() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
