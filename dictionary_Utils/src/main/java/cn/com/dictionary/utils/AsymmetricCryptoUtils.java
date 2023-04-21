package cn.com.dictionary.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * @author gejj
 * @package cn.com.dictionary.common
 * @data 2023/4/20 14:58
 */
public class AsymmetricCryptoUtils {
    private static final Logger logger = LoggerFactory.getLogger(AsymmetricCryptoUtils.class);

    private static final String ALGORITHM= "RSA";

    private static final String ROOT_PATH = ClassUtil.getClassPath();

    /**
     * 根据名字生成对应密钥对
     * 公钥存放地址：{classpath}/cer/{name}.cer
     * 私钥存放地址：{classpath}/cer/{name}.pfx
     * 如果存在对应的公钥或者私钥，将不会重复生成
     * @param name
     * @return
     */
    public static Boolean generateKeyPair(String name){
        KeyPair pair = SecureUtil.generateKeyPair(ALGORITHM);
        //判断私钥和公钥是否已经存在
        String publicKeyPath = "/cer/" + name + ".cer";
        String privateKeyPath = "/cer/" + name + ".pfx";
        String publicUrl = ROOT_PATH + publicKeyPath;
        String privateUrl = ROOT_PATH + privateKeyPath;
        // 只有公钥或者私钥不存在时才生成
        if(!FileUtil.exist(publicUrl) || !FileUtil.exist(privateUrl)){
            //保存私钥
            byte[] privateKey = pair.getPrivate().getEncoded();
            FileUtil.writeBytes(privateKey, privateUrl);
            logger.info("{}私钥的保存地址：{}",privateKey,privateUrl);
            //保存公钥
            String  publicKey = Base64.encode(pair.getPublic().getEncoded());
            FileUtil.writeUtf8String(publicKey, publicUrl);
            logger.info("{}公钥的保存地址：{}",name,publicUrl);
        }else{
            logger.info("{}对应的密钥已存在",name);
            return false;
        }
        return true;
    }

    /**
     * 根据名字获取对应私钥
     * @param name
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String name) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = FileUtil.readBytes(ROOT_PATH + "/cer/" + name + ".pfx");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory instance = KeyFactory.getInstance(ALGORITHM);
        return instance.generatePrivate(keySpec);
    }

    /**
     * 根据名字获取对应公钥
     * @param name
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String name) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String keyStr = FileUtil.readUtf8String(ROOT_PATH + "/cer/" + name + ".cer");
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(keyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 私钥加密
     * @param content
     * @param name
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String rencrypt(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (Strings.isBlank(content)) {
            return "";
        }
        RSA rsa = new RSA(getPrivateKey(name), null);
        byte[] bytes = rsa.encrypt(content, KeyType.PrivateKey);
        return Convert.toHex(bytes);
    }

    /**
     * 公钥解密
     * @param content
     * @param name
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String rdecrypt(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (Strings.isBlank(content)) {
            return "";
        }
        RSA rsa = new RSA(null, getPublicKey(name));
        byte[] bytes = rsa.decrypt(Convert.hexToBytes(content), KeyType.PublicKey);
        return Convert.hexToStr(Convert.toHex(bytes), CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 公钥加密
     * @param content
     * @param name
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String uencrypt(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (Strings.isBlank(content)) {
            return "";
        }
        RSA rsa = new RSA(null, getPublicKey(name));
        byte[] bytes = rsa.encrypt(content, KeyType.PublicKey);
        return Convert.toHex(bytes);
    }

    /**
     * 私钥解密
     * @param content
     * @param name
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String udecrypt(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (Strings.isBlank(content)) {
            return "";
        }
        RSA rsa = new RSA(getPrivateKey(name), null);
        byte[] bytes = rsa.decrypt(Convert.hexToBytes(content), KeyType.PrivateKey);
        return Convert.hexToStr(Convert.toHex(bytes), CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 私钥数据签名，内容进行md5后，使用私钥进行数据加密
     * @param content
     * @param name
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String rsign(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // 内容MD5后私钥数据加密
        String contentMd5 = SecureUtil.md5(content);
        return rencrypt(contentMd5, name);
    }

    /**
     * 公钥数据验签
     * 1.签名内容使用公钥进行数据解密后得到一串md5，此md5值就是私钥签名数据源的md5值
     * 2.把解密得到的md5和实际内容的md5值进行对比，md5值相同，说明私钥签名的数据源和内容一致
     * @param name
     * @param sign
     * @param content
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static boolean rverify(String name, String sign, String content) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        // 签名内容sign公钥数据解密后得到内容MD5
        String rdecrypt = rdecrypt(sign, name);
        // 把解密得到的MD5和实际内容content的MD5进行对比
        return Objects.equals(SecureUtil.md5(content), rdecrypt);
    }

    /**
     * 公钥数据签名，把内容进行MD5后，使用公钥进行数据加密
     * @param content 签名内容
     * @param name 名字
     * @return 签名MD5
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String usign(String content, String name) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        // 内容MD5后私钥数据加密
        String contentMd5 = SecureUtil.md5(content);
        return uencrypt(contentMd5, name);
    }

    /**
     * 私钥数据验签
     * 1.签名内容使用私钥进行数据解密后得到一串MD5 此MD5值就是公钥签名数据源的MD5值
     * 2.把解密得到的MD5和实际内容MD5值进行比对，MD5值相同，说明公钥签名的数据源内容一致
     * @param name 名字
     * @param sign 签名字符串
     * @param content 验证内容
     * @return boolean
     * @throws InvalidKeySpecException the invalid key spec exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException
     */
    public static boolean uverify(String name, String sign, String content) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        // 签名内容sign公钥数据解密后得到内容MD5
        String udecrypt = udecrypt(sign, name);
        // 把解密得到的MD5和实际内容content的MD5进行对比
        return Objects.equals(SecureUtil.md5(content), udecrypt);
    }

}
