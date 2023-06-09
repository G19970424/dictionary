package cn.com.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gejj
 * @package cn.com.dictionary
 * @data 2023/4/25 16:07
 */

@SpringBootApplication
@MapperScan("cn.com.dictionary.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
