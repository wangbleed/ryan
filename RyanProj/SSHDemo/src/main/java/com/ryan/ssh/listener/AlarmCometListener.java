package com.hyron.alarmcenter.listener;

import com.google.gson.Gson;
import com.hyron.alarmcenter.cache.CacheQueueFIFO;
import com.hyron.alarmcenter.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * Created by Ryan on 2015/1/8.
 */
public class AlarmCometListener implements ServletContextListener{

    private CometContext cometContext = null;
    private CometEngine cometEngine = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final CacheQueueFIFO cacheQueueFIFO = CacheQueueFIFO.getInstance();

        cometContext = CometContext.getInstance();
        cometEngine = cometContext.getEngine();
        cometContext.registChannel(Constant.COMET_CHANNEL);

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    CometEngine ce = CometContext.getInstance().getEngine();
                    Object value = cacheQueueFIFO.getDataFromCache();
                    String content = null;
                    if(value != null && (content = String.valueOf(value)) != null && StringUtils.isNotEmpty(content)){
                        ce.sendToAll(Constant.COMET_CHANNEL, content);
                    }
//                    ce.sendToAll(Constant.COMET_CHANNEL, new Gson().toJson(new CometData(Runtime.getRuntime().freeMemory()/1024, new Date())));
                }
            }
        }.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        cometEngine.destroy();
        cometContext.destroy();
    }


    class CometData {
        private long memory;
        private Date dt;

        CometData(long memory, Date dt) {
            this.memory = memory;
            this.dt = dt;
        }

        public Date getDt() {
            return dt;
        }

        public void setDt(Date dt) {
            this.dt = dt;
        }

        public long getMemory() {
            return memory;
        }

        public void setMemory(int memory) {
            this.memory = memory;
        }
    }

}

