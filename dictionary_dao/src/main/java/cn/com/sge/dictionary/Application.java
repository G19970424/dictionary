package cn.com.sge.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gejj
 * @package cn.com.sge.dictionary
 * @data 2023/4/17 17:43
 */
@SpringBootApplication
@MapperScan("cn.com.sge.dictionary.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
