package com.ryan.io.packet;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class MessageContent implements Serializable{
    private Packet msg;
    private boolean compress;
    private boolean crypter;

    public MessageContent(Packet msg) {
        this.msg = msg;
    }

    public Packet getMsg() {
        return msg;
    }

    public MessageContent setMsg(Packet msg) {
        this.msg = msg;
        return this;
    }

    public boolean isCompress() {
        return compress;
    }

    public MessageContent setCompress(boolean compress) {
        this.compress = compress;
        return this;
    }

    public boolean isCrypter() {
        return crypter;
    }

    public MessageContent setCrypter(boolean crypter) {
        this.crypter = crypter;
        return this;
    }
}
