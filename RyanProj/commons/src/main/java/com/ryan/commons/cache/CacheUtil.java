package com.ryan.commons.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ryan on 15/5/13.
 * 缓存工具
 * 一级缓存redis
 * 先从二级缓存读，如果没有从一级缓存读，并放入二级缓存中
 */
public class CacheUtil {

    public static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 最大缓存数量
     */
    @NotNull
    private Long maxCacheNum;
    /**
     * 写完之后过期时间
     */
    @NotNull
    private Long expireAfterWriteTimes;
    /**
     * 刷新后缓存多少时间
     */
    @NotNull
    private Long refreshExpireTimes;

    private Boolean bGetFromRedis = false;

    private RedisClusterClient redisClusterClient;

    public void setbGetFromRedis(Boolean bGetFromRedis) {
        this.bGetFromRedis = bGetFromRedis;
    }

    public void setRedisClusterClient(RedisClusterClient redisClusterClient) {
        this.redisClusterClient = redisClusterClient;
    }

    public long getMaxCacheNum() {
        return maxCacheNum;
    }

    public void setMaxCacheNum(long maxCacheNum) {
        this.maxCacheNum = maxCacheNum;
    }

    public long getRefreshExpireTimes() {
        return refreshExpireTimes;
    }

    public void setRefreshExpireTimes(long refreshExpireTimes) {
        this.refreshExpireTimes = refreshExpireTimes;
    }

    public long getExpireAfterWriteTimes() {
        return expireAfterWriteTimes;
    }

    public void setExpireAfterWriteTimes(long expireAfterWriteTimes) {
        this.expireAfterWriteTimes = expireAfterWriteTimes;
    }

//    LoadingCache<String, Object> cache;
    Cache<String, Object> cache;

    public void initCache(){
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxCacheNum)
                .concurrencyLevel(4)
                .expireAfterWrite(expireAfterWriteTimes, TimeUnit.SECONDS)
//                .refreshAfterWrite(refreshExpireTimes, TimeUnit.SECONDS)
                .build();
//                .build(new CacheLoader<String, Object>() {
//                    @Override
//                    public Object load(String key) throws Exception {
//                        Object result = null;
//                        if(bGetFromRedis){
//                            result = redisClusterClient.getObjectFromRedis(key);
//                        }
//                        return result;
//                    }
//                });

    }

    /**
     * 短暂存储
     * @param key
     * @param value
     */
    public void put2RedisCacheByShortTime(@NotNull String key, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceObjectByShortTime(key, value);
        } catch (Exception e){
            logger.error("persitenceObject to redis error, key:{}, value:{}", key, value);
        }
    }

    /**
     * 放入二级缓存中(普通性存储)
     * @param key
     * @param value
     */
    public void put2RedisCache(@NotNull String key, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceObject(key, value);
        } catch (Exception e){
            logger.error("persitenceObject to redis error, key:{}, value:{}", key, value);
        }
    }

    /**
     * 将session放入redis中
     * @param key
     * @param value
     */
    public void putSession2Redis(@NotNull String key, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceSessionObject(key, value);
        } catch (Exception e){
            logger.error("putSession2Redis to redis error, key:{}, value:{}", key, value);
        }
    }

    /**
     * 将全局性资源放入其中
     * @param key
     * @param value
     */
    public void putResouce2Redis(@NotNull String key, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceResource(key, value);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("putSession2Redis to redis error, key:{}, value:{}", key, value);
        }
    }

    /**
     * 将数据持久化到redis中
     * @param key
     * @param value
     */
    public void putData2RedisByForever(@NotNull String key, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceObjectByForever(key, value);
        } catch (Exception e){
            logger.error("putData2RedisByForever to redis error, key:{}, value:{}", key, value);
        }
    }

    /**
     * 将数据保存至redis中，单位：秒
     * @param key
     * @param second
     * @param value
     */
    public void putData2RedisByTime(@NotNull String key, int second, Object value){
        try{
            if(value != null)
                redisClusterClient.persitenceObject(key, second, value);
        } catch (Exception e){
            logger.error("putData2RedisByTime to redis error, key:{}, second:{}, value:{}", key, second, value);
        }
    }

    /**
     * 将session从redis中获取出来
     * @param key
     * @return
     */
    public <T> T getSessionByFromRedis(@NotNull String key, Class<T> cls){
        return getCacheByFromRedis(key, cls);
    }
    public Object getSessionByFromRedis(@NotNull String key){
        return getCacheByFromRedis(key);
    }

    /**
     * 从一级缓存中读取并放入二级缓存中
     * @param key
     * @return
     */
    public <T> T getCacheByFromRedis(@NotNull String key, Class<T> cls){
        Object obj = getCacheByFromRedis(key);
        if(obj != null)
            return (T) obj;
        return null;
    }
    public Object getCacheByFromRedis(@NotNull String key){
        Object result = null;
        try{
            result = cache.get(key, new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return redisClusterClient.getObjectFromRedis(key);
                }
            });
            return result;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 从其他地方读取放入二级缓存中
     * @param key
     * @param call
     * @return
     */
    public <T> T getCacheByFromOther(@NotNull String key, Callable<Object> call, Class<T> cls){
        Object obj = getCacheByFromOther(key, call);
        if(obj != null)
            return (T) obj;
        return null;
    }
    public Object getCacheByFromOther(@NotNull String key, Callable<Object> call){
        try {
            return cache.get(key, call);
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 三级读取，先从本地cache读取，再从redis读取，最后从自己定义的callable中读取
     * @param key
     * @param call
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getCacheRedisFromOther(@NotNull String key, Callable<Object> call, Class<T> cls){
        Object obj = getCacheRedisFromOther(key, call);
        if(obj != null)
            return (T) obj;
        return null;
    }
    public Object getCacheRedisFromOther(@NotNull String key, Callable<Object> call){
        try{
            Object obj = getCacheByFromRedis(key);
            if(null == obj)
                obj = call.call();
            return obj;
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将全局资源提取出来
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public <T>T getResourceByFromRedis(@NotNull String key, Class<T> cls){
        return getCacheByFromRedis(key, cls);
    }
    public Object getResourceByFromRedis(@NotNull String key){
        return getCacheByFromRedis(key);
    }

    /**
     * 获取短暂时间的存储数据
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getCacheShortTimeByFromRedis(@NotNull String key, Class<T> cls){
        return getCacheByFromRedis(key, cls);
    }
    public Object getCacheShortTimeByFromRedis(@NotNull String key){
        return getCacheByFromRedis(key);
    }

    /**
     * 获取永久数据的存储数据
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getCacheForeverByFromRedis(@NotNull String key, Class<T> cls){
        return getCacheByFromRedis(key, cls);
    }
    public Object getCacheForeverByFromRedis(@NotNull String key){
        return getCacheByFromRedis(key);
    }

    /**
     * 累加器，加1
     * @param key
     * @return
     */
    public Long incKey(@NotNull String key){
        return redisClusterClient.incKey(key);
    }

    /**
     * 累加器，加value
     * @param key
     * @param vale
     * @return
     */
    public Long incKeyBy(@NotNull String key, long vale){
        return redisClusterClient.incKeyBy(key, vale);
    }

    /**
     * 减数器，减1
     * @param key
     * @return
     */
    public Long decrKey(@NotNull String key){
        return redisClusterClient.decrKey(key);
    }

    /**
     * 减数器，减value
     * @param key
     * @param value
     * @return
     */
    public Long decrKeyBy(@NotNull String key, long value){
        return redisClusterClient.decrKeyBy(key, value);
    }

    /**
     * 追加字符串，并返回
     * @param key
     * @param value
     * @return
     */
    public String append(@NotNull String key, String value){
        try {
            return redisClusterClient.append(key, value);
        } catch (Exception e) {
            logger.error("redis append error, cause:{}", e.getMessage());
            return null;
        }
    }

    public void invalidByRedis(@NotNull String key) {
        try{
            invalid(key);
            redisClusterClient.removeObject(key);
        } catch (Exception e){
            logger.error("redis remove error, cause:{}", e.getMessage());
        }
    }

    /**
     * 判断是否存在，如果不获取具体信息的话，效率比get更快，根据
     * 具体业务来选择isExist或get
     * @param key
     * @return
     */
    public boolean isExistByRedis(@NotNull String key){
        try{
            return redisClusterClient.isExist(key);
        } catch (Exception e){
            logger.error("redis exist error, cause:{}", e.getMessage());
            return false;
        }
    }

    /**
     * 将数据放入redis中，但这些数据不能重复（一般用于轨迹等其他不重复数据）
     * @param key
     * @param values
     */
    public void putData2RedisSet(String key, Object... values){
        try {
            redisClusterClient.saveObject2Set(key, values);
        } catch (Exception e ){
            logger.error("redis saveObject2Set error, cause:{}", e.getMessage());
        }
    }

    /**
     * 获取不重复数据集
     * @param key
     * @return
     */
    public <T> Set<T> getDataFromRedisSet(@NotNull String key, Class<T> cls){
        try{
            return redisClusterClient.getSet2Array(key, cls);
        } catch (Exception e){
            logger.error("redis getSet2Array");
            return null;
        }
    }

    public <T> Set<T> getDataFromRedisSetAndClearData(@NotNull String key, Class<T> cls){
        try{
            return redisClusterClient.getSet2Array(key, cls, true);
        } catch (Exception e){
            logger.error("redis getSet2Array");
            return null;
        }
    }

    public void invalid(@NotNull String key){
        cache.invalidate(key);
    }

    public void invalidAll(){
        cache.invalidateAll();
    }

    public ConcurrentMap<String, Object> getAllCache(){
        return cache.asMap();
    }

    /**
     * 判断set中是否存在该object
     * @param key
     * @param obj
     * @return
     */
    public boolean isExistInSet(@NotNull String key, Object obj) {
        try {
            return redisClusterClient.isExistInSet(key, obj);
        } catch (Exception e){
            logger.error("redis isExistInSet");
            return false;
        }
    }

    /**
     * 移除set中得某些值
     * @param key
     * @param obj  为一个数组值，如果是list，set这些请转化为array
     * @return
     */
    public void removeObjectInSet(@NotNull String key, Object... obj) {
        try{
            redisClusterClient.removeObjectInSet(key, obj);
        } catch (Exception e){
            logger.error("redis removeObjectInSet");
        }
    }

}
