package com.ryan.commons.util.thread;

/**
 * Created by ryan on 15/7/2.
 */
public class ThreadUtil {

    public static void doSleep(int sec){
        doSleepByMicSec(sec * 1000);
    }

    public static void doSleepByMicSec(int micSecond){
        try{
            Thread.sleep(micSecond);
        } catch (Exception e){

        }
    }
}
