package com.ryan.boot;

import com.ryan.protocol.protobuf.FirstExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
public class ApplicationBoot {

    public static void main(String[] args){
        SpringApplication.run(ApplicationBoot.class, args);

//        FirstExample.Person.Builder builder = FirstExample.Person.newBuilder();
//        builder.setId(1111);
//        builder.setEmail("wang_bleed@126.com");
//        builder.setName("Ryan");

    }
}
