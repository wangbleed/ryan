package com.ryan.commons.constant;


/**
 * Created by ryan on 15/5/5.
 */
public class Global {

    public static final String MCODE_PATTERN = "^\\d{4}";       // mCode
    public static final String CMD_FIELD = "cmd";               // 常量cmd
    //MQ 队列
    public final static String MQ_HEADER_STATUS_KEY = "1";
    public final static String MQ_EXT_TYPE_KEY = "mqtype";

    //MQ 队列
    public static final String REGEX_PHONE = "^0{0,1}(13[0-9]|15[0|1|2|3|4|5|6|7|8|9]|18[0-9]|14[5|6|7]|177)[0-9]{8}$"; // 手机号码
    public static final String REGEX_PASSWORD = "^(\\w|\\d|_){6,20}$";          // 账户密码
    public static final String REGEX_SENDER_NAME = "^[a-zA-Z\u4e00-\u9fa5\\.]{0,20}$";     // 寄件人姓名
    public static final String REMEMBER_ME_KEY = "rememberMeKey";
    public static final int LOGIN_COOIKE_MAX_AGE = 30*24*60*60;  //30天
    public static final String COOKIE_SECRET = "9048";
    public static final String COOKIE_SEPARATOR = "&&&&";
    public static final int DB_BATCH_PER_COUNT = 200;





}
