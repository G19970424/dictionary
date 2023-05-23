package cn.com.dictionary.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gejj
 * @data 2023/5/8 16:04
 * 仅支持int/integer、double/Double、float/Float、String 数据类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
    String value() default "";
}
