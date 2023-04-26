package cn.com.dictionary.common.response;

import cn.com.dictionary.common.enumdata.ResultCodeEnum;

/**
 * @author gejj
 * @package cn.com.dictionary.common.response
 * @data 2023/4/25 16:28
 * Used as a consistent return interface
 */
public class ApiResult<T> {
    private String code;
    private String message;
    private T data;
    private boolean success;

    protected static <T> ApiResult<T> build(T data){
        ApiResult<T> result = new ApiResult<>();

        if(data != null){
            result.setData(data);
        }
        return result;
    }

    public static <T> ApiResult<T> build(T body, ResultCodeEnum resultCodeEnum){
        ApiResult<T> result = build(body);
        result.setCode(resultCodeEnum.getValue());
        result.setMessage(resultCodeEnum.getLabel());
        return result;
    }

    public static<T> ApiResult<T> ok(){
        return ApiResult.ok(null);
    }

    public static<T> ApiResult<T> ok(T data){
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> ApiResult<T> fail(){
        return ApiResult.fail(null);
    }

    public static<T> ApiResult<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

    public ApiResult<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ApiResult<T> code(String code){
        this.setCode(code);
        return this;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
