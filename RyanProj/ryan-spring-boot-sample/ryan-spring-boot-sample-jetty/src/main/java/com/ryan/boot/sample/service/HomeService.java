package com.ryan.boot.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HomeService {

    @Autowired
    private ServicePropeties servicePropeties;

    public String getMessage(){
        return "Hello, " + servicePropeties.getName();
    }
}
