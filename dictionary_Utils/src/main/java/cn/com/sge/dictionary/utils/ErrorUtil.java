package cn.com.sge.dictionary.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author gejj
 * @package cn.com.sge.dictionary.utils
 * @data 2023/4/19 16:59
 *
 * Capture error log processing tool class
 */
public class ErrorUtil {
    public static String errorInfoToString(Throwable e){
        try(StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)){
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        }catch (Exception ignored){
            throw new RuntimeException(ignored.getMessage(),ignored);
        }
    }
}
