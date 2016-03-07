package com.ryan.commons.util.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

/**
 * Created by admin on 2015/7/6.
 */
public class Md5Util {

    private static PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

    static {
        passwordEncoder = new Md5PasswordEncoder();
        ((Md5PasswordEncoder) passwordEncoder).setEncodeHashAsBase64(true);
    }
    /**
     *
     * @param text 明文
     * @param salt 密码
     * @return
     * @throws IllegalArgumentException
     */
    public static String encrypt(String text, Object salt) throws IllegalArgumentException {
        return passwordEncoder.encodePassword(text, salt);
    }

    /**
     *
     * @param text 明文
     * @param ciphertext 密文
     * @param salt 密码
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean passwordsMatch(String text, String ciphertext, Object salt) throws IllegalArgumentException {
        return passwordEncoder.isPasswordValid(ciphertext, text, salt);
    }


    public static void main(String[] args) {
        String password = Md5Util.encrypt("123456", "yto123456xl");
        System.out.println(password);
    }
}
