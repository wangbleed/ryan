package com.ryan.boot.sample.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@ConfigurationProperties(prefix = "service", ignoreInvalidFields = false)
@Component
public class ServicePropeties {

    private String name = "world";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
