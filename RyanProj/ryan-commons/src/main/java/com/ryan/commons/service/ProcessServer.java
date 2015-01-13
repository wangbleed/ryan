package com.hyron.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan
 */
public class ProcessServer {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Service> serviceMap = new HashMap<String, Service>();

    public void setServiceMap(Map<String, Service> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public void init(){
        //TODO 1、启动所有的Service
        logger.info("start all service ...");
        for(String key : serviceMap.keySet()){
            serviceMap.get(key).init();
            serviceMap.get(key).start();
        }
    }
}
