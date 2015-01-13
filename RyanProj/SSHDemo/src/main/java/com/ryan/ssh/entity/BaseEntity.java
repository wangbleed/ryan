package com.hyron.alarmcenter.entity;

import com.google.gson.Gson;

/**
 * Created by ryan on 2015/1/6.
 */
public class BaseEntity {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
