package cn.com.dictionary.utils.enumdata;

import cn.com.dictionary.utils.AbstractEnum;

/**
 * @author gejj
 * @package cn.com.dictionary.utils.enumdata
 * @data 2023/4/20 17:22
 * user status Enum
 */
public enum UserStatusEnum implements AbstractEnum {
    BLACKLISTED("","黑名单用户"),
    CANCEL("","已注销"),
    ACTIVE("","启用");

    private String label;
    private String value;

    UserStatusEnum(String value,String label) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
