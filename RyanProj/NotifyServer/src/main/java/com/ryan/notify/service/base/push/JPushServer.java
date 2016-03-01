package com.ryan.notify.service.base.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Maps;
import com.ryan.commons.push.JPush;
import com.ryan.commons.util.json.GsonUtil;
import com.ryan.commons.util.security.AES256Encryption;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by ryan on 15/10/20.
 */
@Component
public class JPushServer {

    public static final Logger log = LoggerFactory.getLogger(JPushServer.class);
    public static final Logger pushLog = LoggerFactory.getLogger("PUSH-LOG");
    public final String DEFAULT_IOS_SOUND = "order.m4a";

    @Value("${push.device.ios}")
    private Boolean iosDevice;

    @Value("${push.device.android}")
    private Boolean androidDevice;

    @Value("${push.retryTime}")
    private Integer retryTime;

    @Value("${push.time2live}")
    private Integer time2live;

    @Value("${push.name}")
    private String pushName;

    @Value("${push.appkey}")
    private String appKey;

    @Value("${push.secret}")
    private String masterSecret;

    @Value("${push.ios.product}")
    private Boolean bIosProduct;

    @Value("${jpush.ase.secret}")
    private String aseSecret;

    @Value("${jpush.need.encrypt}")
    private Boolean bNeedEncrypt;

    private JPushClient client = null;

    private Notification notification;
    private Options options;
    private Audience audience;


    private boolean bInit = true;

    public void init(){
        log.info("JPushServer init ...");
        if(StringUtils.isEmpty(masterSecret) || StringUtils.isEmpty(appKey))
            bInit = false;

        options = Options.newBuilder().setTimeToLive(time2live).build();
        options.setApnsProduction(bIosProduct);
    }

    public void start(){
        if(!bInit)
            log.error("JPushServer init error ...");
        log.info("JPushServer--{} start ... AppKey:{}, Secret:{}", pushName, appKey, masterSecret);

        client = new JPushClient(masterSecret, appKey, retryTime);
    }

    public void stop(){
        log.info("JPushServer stop ...");
    }

    public boolean sendJPush(@NotNull JPush jPush) throws Exception{
        boolean bFlag = false;
        PushResult result = client.sendPush(getPushPayload(jPush));
        bFlag = result.isResultOK();
        if(bFlag) {
            pushLog.info("PushStatus:success, jpush:{}", GsonUtil.toJson(jPush));
        } else {
            pushLog.info("PushStatus:error, jpush:{}", GsonUtil.toJson(jPush));
        }
        return bFlag;
    }

    /**
     * 获取JPush的消息体
     * @param jPush
     * @return
     */
    private PushPayload getPushPayload(JPush jPush){
        PushPayload pushPayload =
                PushPayload.newBuilder()
                        .setPlatform(getPlatform(jPush))
                        .setAudience(getAudience(jPush))
                                //通知到显示的push
                        .setNotification(getNotification(jPush))
                                //通知栏不显示的push
//                .setMessage(getMessage(jPush))
                        .setOptions(options)
                        .build();

        return pushPayload;
    }

    /**
     * 根据二进制获取对应的发送平台
     * @param push
     * @return
     */
    private Platform getPlatform(JPush push){
        Platform platform = null;
        int status = push.getPlatformStatus();
        switch (status) {
            case 1:
                platform = Platform.winphone();
                break;
            case 2:
                platform = Platform.android();
                break;
            case 3:
                platform = Platform.android_winphone();
                break;
            case 4:
                platform = Platform.ios();
                break;
            case 5:
                platform = Platform.ios_winphone();
                break;
            case 6:
                platform = Platform.android_ios();
                break;
            default:
                platform = Platform.all();
        }
        return platform;
    }

    /**
     * 获取要Push的用户纬度
     * @param push
     * @return
     */
    private Audience getAudience(JPush push){
        if(push.isAll())
            return Audience.all();
        else if(!push.isAll() && push.getAlias() != null)
            return Audience.alias(push.getAlias());
        else if(!push.isAll() && push.getTagNames() != null)
            return Audience.tag(push.getTagNames());
        else
            return Audience.all();
    }

    /**
     * 获取自定义的Push消息，不显示通知栏
     * @param jPush
     * @return
     */
    private Message getMessage(JPush jPush){
        return Message.newBuilder().addExtra(JPush.JPushExtType.extdata.toString(),
                jPush.getExtMap().get(JPush.JPushExtType.extdata.toString())).build();
    }

    /**
     * 获取基本的Push消息，显示于通知栏
     * @param jPush
     * @return
     */
    private Notification getNotification(JPush jPush){
        IosNotification.Builder iosBuilder = IosNotification.newBuilder();

        IosNotification iosNotification = iosBuilder
                .setAlert(getEncryption(jPush.getContent()))
                .addExtras(getEncrypMap(jPush.getExtMap()))
                .autoBadge()                //边角加1
                .build();

        AndroidNotification androidNotification = AndroidNotification.newBuilder()
                .setAlert(getEncryption(jPush.getContent()))
                .addExtras(getEncrypMap(jPush.getExtMap()))
                .addExtras(jPush.getExtMap())
                .build();

        return Notification.newBuilder()
                .addPlatformNotification(iosNotification)
                .addPlatformNotification(androidNotification)
                .build();
    }

    private String getEncryption(String source) {
        if(!bNeedEncrypt)
            return source;

        if(!StringUtils.isEmpty(source)){
            try {
                return AES256Encryption.encrypt(source, aseSecret);
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    private Map<String, String> getEncrypMap(Map<String, String> extMap){
        if(!bNeedEncrypt)
            return extMap;

        Map<String, String> encryptMap = Maps.newHashMap();
        if(extMap != null && !extMap.isEmpty()){
            for(Map.Entry<String, String> entry : extMap.entrySet()){
                encryptMap.put(entry.getKey(), getEncryption(entry.getValue()));
            }
        }
        return encryptMap;
    }
}
