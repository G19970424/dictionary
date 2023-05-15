package cn.com.dictionary.common.exception;

import cn.com.dictionary.common.GlobalException;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/8 16:16
 * Global Excel Exception
 */
public class ExcelException extends GlobalException implements Serializable {


    private static final long serialVersionUID = 6278314083060702966L;

    public ExcelException(){
        super();
    }

    public ExcelException(String message){
        super(message);
    }
}
