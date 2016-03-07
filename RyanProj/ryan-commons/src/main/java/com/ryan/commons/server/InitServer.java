package com.ryan.commons.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by ryan on 15/5/15.
 */
public class InitServer {

    public static final Logger logger = LoggerFactory.getLogger(InitServer.class);

    static {
        try {
            String confPath = ClassLoader.getSystemResource("").getFile();
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(confPath + "/logback.xml");
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        } catch (JoranException e) {
            logger.error("init logback config error, cause:{}", e.getMessage());
        }
    }

    public static void main(String[] args) {
        logger.info("开始运行Server...");
        new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
    }
}
