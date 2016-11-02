package com.ryan.commons.mq.packet;

import com.ryan.commons.entity.BaseEntity;

/**
 * Created by ryan on 15/5/18.
 */
public class MQPacket extends BaseEntity {

    private static final long serialVersionUID = 769141517465859676L;
    private PacketHeader header;

    private PacketBody body;

    public MQPacket() {
    }

    public MQPacket(PacketBody body, PacketHeader header) {
        this.body = body;
        this.header = header;
    }

    public PacketHeader getHeader() {
        return header;
    }

    public void setHeader(PacketHeader header) {
        this.header = header;
    }

    public PacketBody getBody() {
        return body;
    }

    public void setBody(PacketBody body) {
        this.body = body;
    }

}
