package com.ryan.boot.sample.controller;

import com.ryan.boot.sample.service.HomeService;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
@Description("Demo Controller")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getHello(){
        logger.info("getHello()...");
        return Collections.singletonMap("message", homeService.getMessage());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> olleh(@Validated Message message) {
        logger.info("olleh()...");
        Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("message", message.getValue());
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return model;
    }

    @RequestMapping("/")
    @ResponseBody
    public String getHome(HttpServletRequest request, HttpServletResponse response){
        return homeService.getMessage();
    }

    @RequestMapping("/foo")
    @ResponseBody
    public String foo() {
        logger.error("foo()...");
        throw new IllegalArgumentException("Server error");
    }

    protected static class Message {

        @NotEmpty(message = "Message value cannot be empty")
        private String value;

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
