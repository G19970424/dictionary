package cn.com.dictionary.common.result;

/**
 * @author gejj
 * @data 2023/5/10 14:27
 */
public enum ApiCode {
    /** 系统级Code: 5000内 */
    SUCCESS(200, "操作成功"),
    NOT_PERMISSION(403, "没有权限"),
    NOT_FOUND(404, "你请求的资源不存在"),
    FAIL(500, "操作失败"),
    LOGIN_EXCEPTION(4000, "登陆失败"),
    SYSTEM_EXCEPTION(5000, "系统异常"),
    /** 参数校验级Code: 5001 - 6000 */
    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),
    /** 业务级Code: 6001 - 7000 */
    USER_UNAUTHORIZED(6001, "未授权，请先授权再访问"),
    USER_TWO_PASSWORDS_INCONSISTENT(6002, "两次输入密码不一致"),
    USER_ACCOUNT_REGISTERED(6003, "该账号已被注册"),

    /** 登录异常状态码 */
    PASSWORD_ERROR(6001,"用户名/密码错误"),
    IDENTITY_EXPIRED(6002,"登录身份过期！"),
    LOCKED_ACCOUNT(6003,"账号已被冻结！"),
    EXCESSIVE_ATTEMPTS(6004,"登录次数过多，请稍后登录！"),
    UNAUTHORIZED(6005,"无权访问"),
    UNAUTHENTICATED(6006,"用户未登录！")
    /** 登录异常状态码 */
    ;
    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMessage(int code){
        String val = null;
        for (ApiCode value : ApiCode.values()) {
            if(value.code == code){
                val = value.msg;
            }
        }
        return val;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
