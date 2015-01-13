package com.hyron.commons.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ryan
 */
public abstract class Service extends Thread {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract void init();

}
