package com.ryan.boot.sample.service;

import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HomeService {

    public String getHome(){
        return "Hello, " + new Date();
    }
}
