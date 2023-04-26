package cn.com.dictionary.common.utils;

import cn.com.dictionary.utils.AsymmetricCryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gejj
 * @package cn.com.dictionary.common.utils
 * @data 2023/4/20 15:57
 */
public class EncryptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

    /**
     * 通过公钥加密用户密码
     * @param password
     * @return
     */
    public static String passwordEncrypt(String password){
        String encrypt = "";
        try{
            encrypt = AsymmetricCryptoUtils.uencrypt(password, "password");
        }catch (Exception e){
            logger.error("用户密码加密异常！");
        }
        return encrypt;
    }

    /**
     * 通过私钥解密用户密码
     * @param password
     * @return
     */
    public static String passwordDecrypt(String password){
        String udecrypt = "";
        try{
            udecrypt = AsymmetricCryptoUtils.udecrypt(password, "password");
        }catch (Exception e){
            logger.error("用户密码解密异常！");
        }
        return udecrypt;
    }

}
