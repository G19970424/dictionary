package cn.com.dictionary.common.exception;

import cn.com.dictionary.common.GlobalException;

/**
 * @author gejj
 * @data 2023/5/8 16:16
 * Global Excel Exception
 */
public class ExcelException extends GlobalException {


    public ExcelException(){
        super();
    }

    public ExcelException(String message){
        super(message);
    }
}
