package com.ryan.notify.service.base.sms;

import com.ryan.notify.service.base.sms.SmsMessage;

public interface SmsMessageSender {

	public boolean sendMessage(SmsMessage smsMessage) throws Exception;
}
