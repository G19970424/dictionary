package cn.com.dictionary.config;

import cn.com.dictionary.common.response.ApiResponse;
import cn.com.dictionary.exception.BusinessException;
import cn.com.dictionary.utils.ErrorUtil;
import cn.com.dictionary.utils.enumdata.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author gejj
 * @package cn.com.dictionary.handler
 * @data 2023/4/19 16:42
 */
@RestControllerAdvice
public class ExceptionHandlerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(BusinessException e){
        logger.error(ErrorUtil.errorInfoToString(e));
        return ApiResponse.error(e.getCode(),e.getErrorMsg());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        logger.error(ErrorUtil.errorInfoToString(e));
        return ApiResponse.error(StatusEnum.UNKNOWN.getValue(),
                StatusEnum.UNKNOWN.getLabel());
    }


    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(NullPointerException e) {
        logger.error(ErrorUtil.errorInfoToString(e));
        return ApiResponse.error(StatusEnum.INTERNAL_SERVER_ERROR.getValue(),
                StatusEnum.INTERNAL_SERVER_ERROR.getLabel());
    }
}
