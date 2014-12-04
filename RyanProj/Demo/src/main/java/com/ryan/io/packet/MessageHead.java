package com.ryan.io.packet;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class MessageHead implements Serializable{
    private byte cmdType;
    private String version;
    private int seq;

    public byte getCmdType() {
        return cmdType;
    }

    public MessageHead setCmdType(byte cmdType) {
        this.cmdType = cmdType;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public MessageHead setVersion(String version) {
        this.version = version;
        return this;
    }

    public int getSeq() {
        return seq;
    }

    public MessageHead setSeq(int seq) {
        this.seq = seq;
        return this;
    }
}