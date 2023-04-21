package cn.com.dictionary.common;

import cn.com.dictionary.utils.enumdata.StatusEnum;

/**
 * @author gejj
 * @package cn.com.dictionary.common.response
 * @data 2023/4/19 17:12
 */
public class ApiResult<T> {
    private String code;
    private String msg;
    private T data;
    private Boolean success;

    public ApiResult() {
        this.msg = StatusEnum.SUCCESS.getLabel();
        this.code = StatusEnum.SUCCESS.getValue();
    }

    public ApiResult(Boolean success){
        this.success = success;
    }

    public ApiResult(T data){
        this(StatusEnum.SUCCESS,data);
    }

    public ApiResult(StatusEnum statusEnum, T data){
        this.code = statusEnum.getValue();
        this.msg = statusEnum.getLabel();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
