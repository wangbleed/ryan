package com.hyron.commons.packet;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryan on 2015/1/5.
 */
public class MQPacket implements Serializable {

    //唯一标识，用于包体或确定某位用户
    private String uid;
    //包头
    private PacketHead head;
    //包体
    private Object content;
    //上次修改时间
    private long lastModifiedTime;
    //修改或创建时间
    private long modifiedTime = new Date().getTime();
    //发送次数
    private int retryTime = 0;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public PacketHead getHead() {
        return head;
    }

    public void setHead(PacketHead head) {
        this.head = head;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }
}
