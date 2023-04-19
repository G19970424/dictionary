package cn.com.dictionary.exception;

import cn.com.sge.dictionary.utils.enumdata.StatusEnum;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * @author gejj
 * @package cn.com.dictionary.exception
 * @data 2023/4/19 16:43
 */
public class BusinessException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    private StatusEnum statusEnum;
    private String code;
    private String errorMsg;

    public BusinessException(){
        super();
    }

    public BusinessException(StatusEnum statusEnum){
        logger.error("code = %{} ,msg = %{}", statusEnum.getCode(), statusEnum.getMsg());
        this.statusEnum = statusEnum;
        this.code = statusEnum.getCode();
        this.errorMsg = statusEnum.getMsg();
    }

    public BusinessException(String code,String errorMsg){
        logger.error("code = %{} ,msg = %{}",code,errorMsg);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String code, String errorMsg, Object... args) {
        super("{code:" + code + ",errorMsg:" + String.format(errorMsg, args) + "}");
        this.code = code;
        this.errorMsg = String.format(errorMsg, args);
    }

    public StatusEnum getExceptionEnum() {
        return statusEnum;
    }

    public void setExceptionEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
