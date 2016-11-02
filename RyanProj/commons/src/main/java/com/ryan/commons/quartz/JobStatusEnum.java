package com.ryan.commons.quartz;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by xudongwang on 2016/7/15.
 */
public enum JobStatusEnum {

    NONE("none" , 1),
    NORMAL("运行中", 2),
    PAUSED("暂停中", 3),
    COMPLETE("完成", 4),
    ERROR("出错", 5),
    RUNNING("执行中", 6),
    ;
    private JobStatusEnum(){
    }

    private JobStatusEnum(String name, int code){
        this.name = name;
        this.code = code;
    }

    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static JobStatusEnum getStatus(String name){
        if(StringUtils.isEmpty(name))
            return null;

        for(JobStatusEnum statusEnum : JobStatusEnum.values()){
            if(statusEnum.toString().equalsIgnoreCase(name))
                return statusEnum;
        }
        return  null;
    }
}
