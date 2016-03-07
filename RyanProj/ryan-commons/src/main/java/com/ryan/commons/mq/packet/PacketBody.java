package com.ryan.commons.mq.packet;

import com.ryan.commons.entity.BaseEntity;

import java.util.Map;

/**
 * Created by ryan on 15/5/18.
 */
public class PacketBody extends BaseEntity {

    /**
     * 对象体
     */
    private Object content;

    /**
     * 扩展信息
     */
    private Map<String, String> ext;


    public PacketBody() {
    }

    public PacketBody(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }

}
