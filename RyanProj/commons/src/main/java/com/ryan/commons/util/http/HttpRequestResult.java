package com.ryan.commons.util.http;

import com.ryan.commons.entity.BaseEntity;

/**
 * Created by ryan on 15/5/5.
 */
public class HttpRequestResult extends BaseEntity{
    private int code;
    private String content;
    private byte[] buffer;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }
}