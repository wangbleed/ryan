package com.ryan.commons.enums;

/**
 * Created by xudongwang on 2016/6/22.
 */
public enum RespEnum {

    C1000(1000, "操作成功"),
    C1001(1001, "操作失败"),
    C1002(1002, "参数为空"),
    C1003(1003, "格式不对"),
    C1004(1004, "无此数据"),
    C1005(1005, "无权操作"),

    ;
    RespEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
