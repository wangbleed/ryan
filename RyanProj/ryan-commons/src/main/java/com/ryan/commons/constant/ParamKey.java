package com.ryan.commons.constant;

/**
 * Created by admin on 2015/6/2.
 */
public interface ParamKey {
    interface Page {
        String NUM_KEY = "pageNo";
        String SIZE_KEY = "pageSize";
        String OBJECT = "pageObject";
        String SKIPURL = "pageSkipUrl";
        String DESC = "desc";
        String ASC = "asc";

        int CURRENT_NO = 1;
        int PAGE_SIZE = 10;
    }

}
