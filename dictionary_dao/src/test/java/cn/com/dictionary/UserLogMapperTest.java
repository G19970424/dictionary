package cn.com.dictionary;

import cn.com.dictionary.Application;
import cn.com.dictionary.mapper.IUserLogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gejj
 * @package cn.com.dictionary.test
 * @data 2023/4/20 17:01
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class UserLogMapperTest {
    @Autowired
    private IUserLogMapper userLogMapper;

    @Test
    public void doMain(){
        userLogMapper.queryNumberOfLogins("929e5f89-5a46-47ad-a647-f8ee0b223a70");
    }
}
