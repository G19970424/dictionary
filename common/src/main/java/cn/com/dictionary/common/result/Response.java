package cn.com.dictionary.common.result;

import cn.com.dictionary.common.GlobalException;
import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gejj
 * @data 2023/5/11 14:36
 */

public class Response {

    private static final Logger logger = LoggerFactory.getLogger(Response.class);

    public static void customResponse(int code , String msg, ServletResponse response){
        try {
            Map<String ,Object> map = new HashMap<>();
            map.put("code", code);
            map.put("msg", msg);
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String userJson = JSON.toJSONString(map);
            ServletOutputStream ops = response.getOutputStream();
            ops.write(userJson.getBytes("UTF-8"));
            ops.flush();
        } catch (IOException e) {
            logger.error("custom response error:{}",e.getMessage());
            throw new GlobalException(e);
        }
    }
}
