package cn.com.dictionary.common.response;

import cn.com.sge.dictionary.utils.enumdata.StatusEnum;

/**
 * @author gejj
 * @package cn.com.dictionary.common.response
 * @data 2023/4/19 17:12
 */
public class ApiResultResponse<T> {
    private String code;
    private String msg;
    private T data;

    public ApiResultResponse() {
        this.msg = StatusEnum.SUCCESS.getMsg();
        this.code = StatusEnum.SUCCESS.getCode();
    }

    public ApiResultResponse(T data){
        this(StatusEnum.SUCCESS,data);
    }

    public ApiResultResponse(StatusEnum statusEnum,T data){
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
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
}
