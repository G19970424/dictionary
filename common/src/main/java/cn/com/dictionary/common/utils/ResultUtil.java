package cn.com.dictionary.common.utils;

import cn.com.dictionary.common.result.ApiCode;
import cn.com.dictionary.common.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author gejj
 * @data 2023/5/15 15:34
 */
public class ResultUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static ApiResult SUCCESS(){
        ApiResult apiResult = new ApiResult();
        apiResult.setDate(new Date());
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static ApiResult SUCCESS(String msg){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(msg);
        apiResult.setDate(new Date());
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static ApiResult SUCCESS(String msg,int code){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(msg);
        apiResult.setCode(code);
        apiResult.setDate(new Date());
        apiResult.setSuccess(true);
        return apiResult;
    }


    public static ApiResult SUCCESS(String msg,Object data){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(msg);
        apiResult.setData(data);
        apiResult.setDate(new Date());
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static ApiResult SUCCESS(String msg,int code,Object data){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(msg);
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setDate(new Date());
        apiResult.setSuccess(true);
        return apiResult;
    }

    public static ApiResult FAIL(int code){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(ApiCode.getMessage(code));
        apiResult.setCode(code);
        apiResult.setSuccess(false);
        apiResult.setDate(new Date());
        return apiResult;
    }

    public static ApiResult FAIL(String msg){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(msg);
        apiResult.setSuccess(false);
        apiResult.setDate(new Date());
        return apiResult;
    }
}
