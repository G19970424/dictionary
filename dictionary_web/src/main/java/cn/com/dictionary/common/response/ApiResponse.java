package cn.com.dictionary.common.response;

import java.util.HashMap;

/**
 * @author gejj
 * @package cn.com.dictionary.common.response
 * @data 2023/4/19 17:04
 */
public class ApiResponse extends HashMap<String,Object> {
    public ApiResponse() {
        put("code", 200);
        put("msg", "success");
    }

    public ApiResponse(String code, String msg) {
        super(2);
        put("code", code);
        put("msg", msg);
    }

    public static ApiResponse ok() {
        ApiResponse r = new ApiResponse();
        r.put("msg", "操作成功");
        return r;
    }

    public static ApiResponse msg(String msg) {
        ApiResponse r = new ApiResponse();
        r.put("msg", msg);
        return r;
    }

    public static ApiResponse ok(Object obj) {
        ApiResponse r = new ApiResponse();
        r.put("code", 200);
        r.put("results", obj);
        return r;
    }

    public static ApiResponse error() {
        return error("500", "未知异常，请联系管理员");
    }

    public static ApiResponse error(String msg) {
        return error("500", msg);
    }

    public static ApiResponse error(String code, String msg) {
        ApiResponse r = new ApiResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    @Override
    public ApiResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    public ApiResponse page(Object page) {
        return put("page", page);
    }


    public ApiResponse result(Object obj) {
        super.put("result", obj);
        return this;
    }
}
