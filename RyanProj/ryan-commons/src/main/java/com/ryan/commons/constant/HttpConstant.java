package com.ryan.commons.constant;

import org.apache.http.protocol.HTTP;

/**
 * Created by ryan on 15/5/5.
 * 定义HTTP一些常用变量
 */
public class HttpConstant {

    public static final int HTTP_CONNECT_TIMEOUT = 5000;
    public static final int HTTP_CONNECT_REQUEST_TIMEOUT = 5000;
    public static final int HTTP_SOCKET_TIMEOUT = 5000;

    public static final String DEFAULT_HTTP_CHARSET = HTTP.UTF_8;

    /**
     * 自定义http头
     */
    public static final String SXG_APP_HTTP_HEADER = "sxg_app_client";
    public static final String SXG_APP_HTTP_HEADER_VALUE = "sxg_app_ios/android";
    public static final String SXG_VER_HTTP_HEADER = "sxg_ver";
    public static final String SXG_VER_HTTP_HEADER_VALUE = "2.0";



}
