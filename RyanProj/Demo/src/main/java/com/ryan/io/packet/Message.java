package com.ryan.io.packet;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
public final class Message implements Serializable{

    private MessageHead head;
    private MessageContent content;

    private int retryTime;

    public MessageHead getHead() {
        return head;
    }

    public Message setHead(MessageHead head) {
        this.head = head;
        return this;
    }

    public MessageContent getContent() {
        return content;
    }

    public Message setContent(MessageContent content) {
        this.content = content;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public Message setRetryTime(int retryTime) {
        this.retryTime = retryTime;
        return this;
    }
}

