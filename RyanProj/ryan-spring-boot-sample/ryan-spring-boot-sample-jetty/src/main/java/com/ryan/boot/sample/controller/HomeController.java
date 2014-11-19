package com.ryan.boot.sample.controller;

import com.ryan.boot.sample.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping("/")
    @ResponseBody
    public String getHome(HttpServletRequest request, HttpServletResponse response){
        return homeService.getHome();
    }

}
