package cn.com.dictionary;

import cn.com.dictionary.service.IKeyStoreService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gejj
 * @package cn.com.dictionary
 * @data 2023/4/21 10:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class KeyStoreServiceTest {

    @Autowired
    private IKeyStoreService keyStoreService;


}
