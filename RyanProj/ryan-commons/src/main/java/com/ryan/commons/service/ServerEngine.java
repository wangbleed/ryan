package com.ryan.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan  15/5/15.
 */
public class ServerEngine {

    public static final Logger logger = LoggerFactory.getLogger(ServerEngine.class);

    private Map<String, IService> serviceMap = new HashMap<String, IService>();

    public void setServiceMap(Map<String, IService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public void init(){
        //TODO 1、启动所有的Service
        logger.info("start all service ...");
        for(String key : serviceMap.keySet()){
            serviceMap.get(key).start();
        }
    }

    public void stop(){
        //TODO 1、关闭所有的Service
        logger.info("stop all service ...");
        for(String key : serviceMap.keySet()){
            serviceMap.get(key).stop();
        }
    }
}
