package com.ryan.ssh.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan on 2015/1/8.
 * 做一个缓存队列，用于将mq里的数据放入其中，
 * 安装FIFO的原则发送给客户端，现在缓存队列默认的值是100w的数据，缓存时间为10s
 */
public class CacheQueueFIFO {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static CacheQueueFIFO instance;

    private Cache<String, Object> cache;
    private ConcurrentLinkedQueue<String> queue;
    //最大缓存实体数为100W
    @Value("#{config['maxCacheNum']}")
    private long maxCacheNum = 1_000_000;
    //最大缓存时间为10s
    @Value("#{config['expireAfterWrite']}")
    private int expireAfterWrite = 10;

    private CacheQueueFIFO(){
        init();
    }
    public static CacheQueueFIFO getInstance(){
        if(null == instance){
            synchronized (CacheQueueFIFO.class){
                instance = new CacheQueueFIFO();
            }
        }
        return instance;
    }

    public void init(){
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxCacheNum)
                .expireAfterAccess(expireAfterWrite, TimeUnit.SECONDS)
                .concurrencyLevel(4)
                .removalListener(new RemovalListener<String, Object>() {
            @Override
            public void onRemoval(RemovalNotification<String, Object> notification) {
                String key = notification.getKey();
                Preconditions.checkNotNull(key, "removed key is null...");
                queue.remove(key);
            }
        }).build();

        queue = new ConcurrentLinkedQueue<String>();
    }

    public synchronized void addData2Cache(Object value){
        Preconditions.checkNotNull(value, "value is null,can't save cache");
        String key = getUUID();
        cache.put(key, value);
        queue.offer(key);
    }

    public synchronized Object getDataFromCache(){
        if(queue.isEmpty() || cache.asMap().isEmpty()){
            return null;
        }
        String key = queue.poll();
        Object value = null;
        try {
            value = cache.get(key, new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
           logger.error("cache read error, key:{}, maybe is expire", key);
        }
        if( value != null){
            cache.invalidate(key);
        }
        return value;
    }

    private String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
