package com.ryan.commons.enums;

/**
 * Created by admin on 2015/10/29.
 */
public enum SmsEnum {
    success;

    /**
     * 状态
     */
    public enum SmsStatusEnum {
        SENDING((byte)1, "发送中"),
        SENDOUT((byte)2, "已发送"),
        FAILED((byte)3, "发送失败");

        private Byte code;
        private String desc;

        private SmsStatusEnum(Byte code, String desc) {
            this.code = code;
            this.desc = desc;
        }


        public Byte getCode() {
            return code;
        }

        public void setCode(Byte code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

    /**
     * 优先级  1，2，3，4，5数字越大，等级越高
     */
    public enum SmsPriorityEnum {
        one((byte)1), two((byte)2), three((byte)3), four((byte)4), five((byte)5);

        private Byte code;

        private SmsPriorityEnum(Byte code) {
            this.code = code;
        }


        public Byte getCode() {
            return code;
        }

        public void setCode(Byte code) {
            this.code = code;
        }
    }
}
