package com.ryan.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ryan on 15/5/15.
 */
public abstract class AbstractService implements IService{

    public static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    @Override
    public void start() {
        logger.info("start service ... serviceName:{}", this.getClass().getName());

        beforeStart();

        run();

        afterStart();
    }

    @Override
    public void stop() {

        logger.info("stop service ... serviceName:{}", this.getClass().getName());

        beforeStop();

        afterStop();

    }
}
