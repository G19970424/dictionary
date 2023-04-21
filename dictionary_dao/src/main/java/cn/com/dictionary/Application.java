package cn.com.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gejj
 * @package cn.com.dictionary
 * @data 2023/4/17 17:43
 */
@SpringBootApplication
@MapperScan("cn.**.mapper")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("Data Layer Init Success!");
    }
}
