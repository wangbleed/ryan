package com.ryan.commons.push;

import com.google.common.collect.Maps;
import com.ryan.commons.entity.BaseEntity;

import java.util.Map;

/**
 * Created by ryan on 15/10/20.
 */
public class JPush extends BaseEntity{

    /**
     * extdata：用于抢单
     * extcontent：用于推送普通的信息
     */
    public enum JPushExtType{
        extdata,
        extcontent
    }

    public final static String extdata = JPushExtType.extdata.toString();
    private static final long serialVersionUID = 3661251869282483279L;

    /**
     * platform 顺序
     * all/ios/android/winphone
     * extMap:extdata
     * 1/1/1/1
     */
    private boolean all;
    private boolean ios;
    private boolean android;
    private boolean winphone;
    private int platformStatus = 0;

    public int getPlatformStatus() {
        return platformStatus;
    }

    private String title;

    public JPush() {
    }

    public JPush(String[] alias, Map<String, String> extMap, String title, String content) {
        this.alias = alias;
        this.extMap = extMap;
        this.title = title;
        this.content = content;
    }

    /**
     * 发送内容
     */
    private String content;

    private Map<String, String> extMap = Maps.newHashMap();

    /**
     * 是否所有人，如果true，则alias与tagname失效；如果false，则根据alias或tagname发送
     */
    private boolean isAllAudience;
    private String[] alias;
    private String[] tagNames;

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        if(all && !this.all)
            platformStatus += 2 << 2;
        else if(!all && this.all)
            platformStatus -= 2 << 2;

        this.all = all;
    }

    public boolean isIos() {
        return ios;
    }

    public void setIos(boolean ios) {
        if(ios && !this.ios)
            platformStatus += 2 << 1;
        else if (!ios && this.ios)
            platformStatus -= 2 << 1;

        this.ios = ios;
    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        if(android && !this.android)
            platformStatus += 2 << 0;
        else if(!android && this.android)
            platformStatus -= 2 << 0;

        this.android = android;
    }

    public boolean isWinphone() {
        return winphone;
    }

    public void setWinphone(boolean winphone) {
        if(winphone && ! this.winphone)
            platformStatus += 1;
        else if(!winphone && this.winphone)
            platformStatus -= 1;

        this.winphone = winphone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAllAudience() {
        return isAllAudience;
    }

    public void setAllAudience(boolean isAllAudience) {
        this.isAllAudience = isAllAudience;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String... alias) {
        this.alias = alias;
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String... tagNames) {
        this.tagNames = tagNames;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
