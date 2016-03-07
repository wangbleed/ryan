package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * Created by admin on 2015/10/26.
 */
public class JGUser implements Serializable {
    private String userId;                      //  员工工号
    private String userName;                    //  员工名称
    private String displayName;                 //  名称
    private String orgCode;                     //  网店代码

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
