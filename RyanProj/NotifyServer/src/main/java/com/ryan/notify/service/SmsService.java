package com.ryan.notify.service;

import com.ryan.commons.service.AbstractService;
import com.ryan.commons.service.RegServiceVo;
import com.ryan.notify.service.base.sms.SMSReceiveClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ryan on 15/5/15.
 */
@Component
public class SmsService extends AbstractService {

    @Autowired
    private SMSReceiveClient smsReceiveClient;

    public void init() {

    }

    public RegServiceVo getRegServiceVo() {
        return null;
    }

    public void beforeStart() {

    }

    public void afterStart() {

    }

    public void beforeStop() {

    }

    public void afterStop() {

    }

    public void run() {
        smsReceiveClient.run();
    }
}
