package cn.com.dictionary.common.enumdata;

/**
 * @author gejj
 * @data 2023/5/18 10:18
 * 用户类型枚举类
 */
public enum RoleEnum {
    ORDINARY_USER("ORDINARY_USER","普通用户"),
    ADVANCED_USER("ADVANCED_USER","高级用户"),
    PRIVILEGED_USER("PRIVILEGED_USER","特级用户"),
    SUPER_USER("SUPER_USER","超级用户"),
    ADMIN("ADMIN","管理员"),
    SUPER_ADMIN("SUPER_ADMIN","超级管理员");

    RoleEnum(String key,String name){
        this.key = key;
        this.name = name;
    }

    private String key;
    private String name;
}
