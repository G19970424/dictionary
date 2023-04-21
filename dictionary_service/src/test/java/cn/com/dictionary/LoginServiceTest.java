package cn.com.dictionary;

import cn.com.dictionary.common.ApiResult;
import cn.com.dictionary.mapper.pojo.UserPojo;
import cn.com.dictionary.service.ILoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gejj
 * @package cn.com.dictionary
 * @data 2023/4/21 11:19
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class LoginServiceTest {
    @Autowired
    private ILoginService loginService;

    @Test
    public void loginTest(){
        ApiResult<UserPojo> authenticate = loginService.authenticate("admin", "admin");

    }
}
