package cn.com.dictionary.common.enumdata;

/**
 * @author gejj
 * @package cn.com.dictionary.enumdata
 * @data 2023/4/19 16:45
 */
public enum ResultCodeEnum implements AbstractEnum {
    SUCCESS("200","请求成功！"),
    FAIL("201","操作失败"),
    SERVER_ERROR("202","服务器异常"),

    // 400
    BAD_REQUEST("400", "请求数据格式不正确!"),
    UNAUTHORIZED("401", "登录凭证过期!"),
    FORBIDDEN("403", "没有访问权限!"),
    NOT_FOUND("404", "请求的资源找不到!"),
    // 500
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVICE_UNAVAILABLE("503", "服务器正忙，请稍后再试!"),
    // 未知异常
    UNKNOWN("10000", "未知异常!"),
    // 自定义
    IS_NOT_NULL("10001","%s不能为空");

    ResultCodeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    private String value;
    private String label;


    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
