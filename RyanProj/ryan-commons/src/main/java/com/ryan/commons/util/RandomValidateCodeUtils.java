package com.ryan.commons.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * Created by bin on 2015/6/24.
 */
public class RandomValidateCodeUtils {
    private static final Integer DEFAULT_SIZE = 6;
    public final static long FIX_LONG = 113362802868l;
    private static final String[] DICTIONARIES = new String[]{"1", "2", "3",
            "4", "5", "6", "7", "8", "9", "0"};

    public static String createValidateCode(Integer size) {
        return RandomStringUtils.randomNumeric(size);

        /*for(int i=0;i<size;i++){
            Integer nextIndex = RandomUtils.nextInt(DICTIONARIES.length);
            code.append(DICTIONARIES[nextIndex]);
        }*/
    }

    public static String createValidateCode() {
        return createValidateCode(DEFAULT_SIZE);
    }

    /**
     * @description 创建快递单密码*/
    public static String createExpressPwd(int i){
        Random r=new Random();
        String pwdPrefix="";
        int j = 0;
        String pwdSuffix = "";
        while(j<i){
            pwdSuffix = pwdSuffix + r.nextInt(10);
            j++;
        }
        return pwdPrefix+pwdSuffix;
    }

    /**
     * ordercode 非空 重复
     * @param random
     * @param i
     * @return
     */
    public static String getOrderCode(String random,int i) {
        random = createExpressPwd(i);
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(1).append(random).append(random);
        String prefix = String.valueOf(Long.valueOf(sBuffer.toString()) + FIX_LONG);
        return prefix + random;

    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        System.out.println(RandomValidateCodeUtils.createExpressPwd(6));
        //System.out.println(RandomValidateCodeUtils.getOrderCode("",11));
    }

}
