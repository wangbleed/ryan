package com.ryan.entity;

import com.ryan.packet.Packet;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class MsgPacket extends Packet {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
