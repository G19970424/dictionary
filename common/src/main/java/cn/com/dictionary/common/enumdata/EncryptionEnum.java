package cn.com.dictionary.common.enumdata;

/**
 * @author gejj
 * @data 2023/5/16 14:48
 */
public enum EncryptionEnum {
    MD5("MD5");


    private String value;

    EncryptionEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
