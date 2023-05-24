package cn.com.dictionary.common.enumdata;

/**
 * @author gejj
 * @data 2023/5/24 14:28
 */
public enum TimeEnum {
    TEN_MINUTE("10 MINUTE","TEN_MINUTE"),
    THIRTY_MINUTE("30 MINUTE","THIRTY_MINUTE"),
    ONE_HOUR("1 HOUR","ONE_HOUR"),
    ONE_DAY("1 DAY","ONE_DAY"),
    THREE_DAY("3 DAY","THREE_DAY"),
    FIVE_DAY("5 DAY","FIVE_DAY"),
    FIFTEEN_DAY("15 DAY","FIFTEEN_DAY"),
    ONE_MONTH("1 MONTH","ONE_MONTH"),
    THREE_MONTH("3 MONTH","THREE_MONTH"),
    SIX_MONTH("6 MONTH","SIX_MONTH"),
    TWELVE_MONTH("12 MONTH","TWELVE_MONTH")
    ;
    private String key;
    private String value;

    TimeEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
