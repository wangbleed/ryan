package com.ryan.notify.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ryan on 15/5/13.
 */
@Component("generatLogJob")
public class GeneratLogJob {

    public static final Logger sendSMSLog = LoggerFactory.getLogger("SENDLOG-LOG");

    public void printLogHeader(){
        sendSMSLog.info("Generate sms log ...");
    }

}
