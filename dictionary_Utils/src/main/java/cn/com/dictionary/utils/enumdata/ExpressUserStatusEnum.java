package cn.com.dictionary.utils.enumdata;

import cn.com.dictionary.utils.AbstractEnum;

/**
 * @author gejj
 * @package cn.com.dictionary.utils.enumdata
 * @data 2023/4/21 11:32
 */
public enum ExpressUserStatusEnum implements AbstractEnum {


    ;
    private String value;
    private String label;

    ExpressUserStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
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
