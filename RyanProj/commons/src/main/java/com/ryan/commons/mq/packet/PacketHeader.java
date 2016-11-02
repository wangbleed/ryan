package com.ryan.commons.mq.packet;

import com.ryan.commons.entity.BaseEntity;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ryan on 15/5/18.
 */
public class PacketHeader extends BaseEntity {

    /**
     * 消息的唯一标示
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * 创建时间
     */
    private long createdTime = new Date().getTime();

    /**
     * 上次修改时间
     */
    private long lastModifiedTime = new Date().getTime();

    /**
     * 消息状态
     */
    private String status;

    /**
     * 发送次数
     */
    private int retryTime = 0;

    /**
     * 扩展信息
     */
    private Map<String, String> ext;

    public PacketHeader() {
    }

    public PacketHeader(String status) {
        this.status = status;
    }

    public PacketHeader(String status, Map<String, String> ext) {
        this.status = status;
        this.ext = ext;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public void addRetryTime(){
        this.retryTime ++;
    }

}
