package com.ryan.commons.util;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.nio.ByteBuffer;

/**
 * Created by ryan on 15/6/5.
 */
public class Uuid {

    private byte[] uid = null;
    private String str = null;

    public Uuid(){
        str = java.util.UUID.randomUUID().toString().toLowerCase().replace("-","");
    }

    @Override
    public String toString() {
        return str;
    }

    public String wrap(byte[] uid){
        try{
            str = new String(uid, "utf-8");
        } catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public String wrap(ByteBuffer uid){
        return wrap(uid.array());
    }

    public byte[] wrap(String uid){
        try {
            this.uid = uid.getBytes("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.uid;
    }

    public String getUIDStr(){
        return str;
    }

    public byte[] getUIDByte(){
        return this.uid;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public int compareTo(Object obj){
        return CompareToBuilder.reflectionCompare(this, obj);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public static void main(String[] args) {
        Uuid uid = new Uuid();
        System.out.println(uid.toString());
    }

}
