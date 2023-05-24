package cn.com.dictionary.common.exception;

import cn.com.dictionary.common.GlobalException;

/**
 * @author gejj
 * @data 2023/5/24 15:44
 */
public class RegisterException extends GlobalException {

    public RegisterException(){
        super();
    }

    public RegisterException(String message){
        super(message);
    }
}
