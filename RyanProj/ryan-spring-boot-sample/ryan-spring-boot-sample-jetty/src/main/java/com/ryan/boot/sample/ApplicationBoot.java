package com.ryan.boot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ApplicationBoot {

    public static void main(String[] args){
        System.out.println("#FFFFFFFFFFFF");
//        SpringApplication.run(ApplicationBoot.class, args);

        new Health.Builder().withDetail("alarm", "ok").status("500").down().build();
    }
}
