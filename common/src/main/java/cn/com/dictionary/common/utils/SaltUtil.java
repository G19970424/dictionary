package cn.com.dictionary.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * @author gejj
 * @data 2023/5/16 10:02
 */
public class SaltUtil {
    static char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+".toCharArray();
    static int length = 32;

    public static String getSalt(int saltLength){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < saltLength; i++) {
            char aChar =chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static String getSalt(){
        return getSalt(length);
    }

    public static void main(String[] args) {
        String salt = SaltUtil.getSalt();
        System.out.println(salt);
        ByteSource bytes = ByteSource.Util.bytes(salt);

        String s = new SimpleHash("MD5", "admin", salt, 8).toHex();
        System.out.println(s);

    }
}
