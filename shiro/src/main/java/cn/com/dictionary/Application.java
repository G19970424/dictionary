package cn.com.dictionary;

import cn.com.dictionary.handler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gejj
 * @data 2023/5/15 14:41
 */
@SpringBootApplication(scanBasePackageClasses = GlobalExceptionHandler.class,scanBasePackages ={"cn.com.dictionary.config","cn.com.dictionary.controller","cn.com.dictionary.service","cn.com.dictionary.dao.mapper"} )
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
