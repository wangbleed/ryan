package com.ryan.boot.sample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-20
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
public class LoginLogAop {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning("execution(* com.ryan.boot.sample..*Service.*(..))")
    public void logSucess(JoinPoint joinPoint){
        logger.info("login sucess...");
        System.out.println("Completed: " + joinPoint);
    }
}
