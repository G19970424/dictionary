package cn.com.dictionary.common;

/**
 * @author gejj
 * @data 2023/5/8 15:39
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 6648159523610993475L;

    private String message;
    private Integer code;

    public GlobalException(){
        super();
    }

    public GlobalException(String message){
        super(message);
        this.message = message;
    }

    public GlobalException(Integer code){
        super();
        this.code = code;
    }

    public GlobalException(String message,Integer code){
        super(message);
        this.code = code;
        this.message = message;
    }

    public GlobalException(Throwable t){
        super(t);
    }

}
