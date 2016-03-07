package com.ryan.commons.enums;

/**
 * Created by admin on 2015/10/31.
 */
public enum CommonEnum {
    VALID((byte)1, "有效"), INVALID((byte)0, "无效");



    /**
     * 省 市 县
     */
    public enum RegionEnum {
        PROVINCE, CITY, AREA;
    }
    /**
     * 省 市 县
     */
    public enum ReportEnum {
        HOUR((byte)0, "小时"),
        DAY((byte)1, "天"),
        MONTH((byte)2, "月"),
        USER_TOTAL((byte)3, "用户历史总数"),
        AVG((byte)4, "全网平均");
        private Byte code;
        private String desc;

        private ReportEnum(Byte code, String desc) {
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

    private Byte code;
    private String desc;

    private CommonEnum(Byte code, String desc) {
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
