package cn.com.test;

import cn.com.dictionary.Application;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gejj
 * @data 2023/5/19 16:23
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class UUIDTest {

    @Autowired
    private IUserService userService;

    @Test
    public void idTest(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setSex(false);
        user.setStatus(0);
        userService.register(user);
    }
}
