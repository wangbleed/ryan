package com.ryan.commons.util.cache;

import com.ryan.commons.constant.CacheConstant;
import com.ryan.commons.util.io.ConnectionIO;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ryan on 15/6/15.
 */
public class RedisClusterClient implements ConnectionIO {

    public static final Logger logger = LoggerFactory.getLogger(RedisClusterClient.class);

    private RedisConfig redisConfig;
    private JedisCluster cluster;
    private Set<HostAndPort> clusterNodes;
    private Charset defaultChartset = Charset.forName("UTF-8");

    @NotNull
    private String ipArr;
    @NotNull
    private Integer port;
    @NotNull
    private Integer timeout;

    public String getIpArr() {
        return ipArr;
    }

    public void setIpArr(String ipArr) {
        this.ipArr = ipArr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Override
    public void init() {
        clusterNodes = Sets.newHashSet();
        String[] ips = ipArr.split(",");
        String[] ipArr = null;
        for(String ip : ips){
            ipArr = ip.split(":");
            clusterNodes.add(new HostAndPort(ipArr[0], Integer.parseInt(ipArr[1])));
        }
        connect();
    }

    @Override
    public boolean connect() {
        if(clusterNodes != null && !clusterNodes.isEmpty()) {
            cluster = new JedisCluster(clusterNodes, timeout, redisConfig.getJedisPoolConfig());
            logger.info("connect redis pool success");
            return true;
        }
        return false;
    }

    @Override
    public boolean disconnect() {
        if(cluster != null){
            try {
                cluster.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("disconnect redis pool success");
            return true;
        }
        return false;
    }

    public void persitenceObject(String key, int deadLineSeconds, Object object) throws IOException{
        persitenceObjectSerializable(key, deadLineSeconds, object);
    }

    public void persitenceObjectByShortTime(String key, Object object) throws IOException {
        persitenceObjectSerializable(key, redisConfig.getCacheShortTime(), object);
    }

    public void persitenceObject(String key, Object object) throws IOException {
        persitenceObjectSerializable(key, redisConfig.getCacheDeadlineTime(), object);
    }

    public void persitenceSessionObject(String key, Object session) throws IOException {
        persitenceObjectSerializable(key, redisConfig.getCacheSessionDeadLineTime(), session);
    }

    public void persitenceResource(String key, Object object) throws IOException {
        persitenceObjectSerializable(key, redisConfig.getCacheResoureDeadlintTime(), object);
    }

    public void persitenceObjectByForever(String key, Object object) throws IOException {
        persitenceObjectSerializable(key, redisConfig.getCacheForever(), object);
    }

    public void removeObject(String key) throws IOException {
        if(cluster.exists(key.getBytes(defaultChartset)))
            cluster.del(key.getBytes(defaultChartset));
    }

    /**
     * 可以直接序列化的保存
     * @param key
     * @param object
     * @throws java.io.IOException
     */
    private void persitenceObjectSerializable(String key, int deadLineSeconds, Object object) throws IOException {
        cluster.setex(key.getBytes(defaultChartset), deadLineSeconds, serializableObject(object));
    }

    private byte[] serializableObject(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(object);
        return baos.toByteArray();
    }

    private Object unSerializableObject(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream input = new ObjectInputStream(bais);
        return input.readObject();
    }

    /**
     * 累加器
     * @param key
     * @return
     */
    public Long incKey(String key){
        return cluster.incr(key.getBytes(defaultChartset));
    }
    /**
     * 累加器，累加count
     * @param key
     * @param count
     * @return
     */
    public Long incKeyBy(String key, long count){
        return cluster.incrBy(key.getBytes(defaultChartset), count);
    }

    /**
     * 减数器
     * @param key
     * @return
     */
    public Long decrKey(String key){
        return cluster.decr(key.getBytes(defaultChartset));
    }
    /**
     * 减数器，递减count
     * @param key
     * @param count
     * @return
     */
    public Long decrKeyBy(String key, long count){
        return cluster.decrBy(key.getBytes(defaultChartset), count);
    }

    /**
     * 追加字符串，并返回
     * @param key
     * @param value
     * @return
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public String append(String key, String value) throws IOException, ClassNotFoundException {
        Long l = cluster.append(key.getBytes(defaultChartset), value.getBytes(defaultChartset));
        if(l > 0){
            return getStringFromRedis(key);
        }
        return null;
    }

    /**
     * 判断redis中是否存在
     * @param key
     * @return
     * @throws Exception
     */
    public boolean isExist(String key) throws Exception {
        return cluster.exists(key.getBytes(defaultChartset));
    }

    /**
     * 从redis里获取
     * @param key
     * @param call
     * @return
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public String getStringFromRedis(String key, Callable<String> call)  throws Exception {
        String entity = getStringFromRedis(key);
        if(null == entity){
            return call.call();
        }
        return entity;
    }

    public String getStringFromRedis(String key) throws IOException, ClassNotFoundException {
        Object obj = getObjectFromRedis(key);
        if(obj != null)
            return String.valueOf(obj);
        return null;
    }

    public Object getObjectFromRedis(String key) throws IOException, ClassNotFoundException {
        byte[] buffer = cluster.get(key.getBytes(defaultChartset));
        if(buffer != null && buffer.length > 0)
            return unSerializableObject(buffer);
        return null;
    }

    public Object getObjectFromRedis(String key, Callable<Object> call) throws Exception {
        Object object = getObjectFromRedis(key);
        if(null == object)
            return call.call();
        return object;
    }

    /**
     * 清除该队列
     * @param queueName 	队列名
     */
    public void clearQueue(String queueName){
        cluster.del(queueName);
    }

    /**
     * 压入队列
     * @param queueName 	队列名
     * @param msg			消息体
     */
    public void pushMsg(String queueName, String msg){
        // 如果队列不存在，说明该队列暂无持久化；如果存在则已持久化
        // 将队列持久化
        if(!cluster.exists(queueName))
            cluster.persist(queueName);
        cluster.lpush(queueName, msg);
    }

    /**
     * 将无重复的数据压入集合中
     * @param key
     * @param objs
     * @throws Exception
     */
    public void saveObject2Set(String key, Object... objs) throws Exception {
        for(Object obj : objs){
            saveObject2Set(key, obj);
        }
    }

    /**
     * 将数据压入无重复的集合中
     * @param key
     * @param obj
     * @throws Exception
     */
    public void saveObject2Set(String key, Object obj) throws Exception {
        cluster.sadd(key.getBytes(defaultChartset), serializableObject(obj));
    }

    /**
     * 判断set中是否存在该object
     * @param key
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean isExistInSet(String key, Object obj) throws Exception {
        return cluster.sismember(key.getBytes(defaultChartset), serializableObject(obj)).booleanValue();
    }

    /**
     * 将set中移除对应obj
     * @param key
     * @param objs
     * @return
     * @throws Exception
     */
    public void removeObjectInSet(String key, Object... objs) throws Exception {
        for(Object obj : objs){
            removeObjectInSet(key, obj);
        }
    }
    public void removeObjectInSet(String key, Object obj) throws Exception {
        cluster.srem(key.getBytes(defaultChartset), serializableObject(obj));
    }

    /**
     * 得到无重复的集合
     * @param key
     * @return
     * @throws Exception
     */
    public <T> Set<T> getSet2Array(String key, Class<T> cls) throws Exception {
        Set<byte[]> result = cluster.smembers(key.getBytes(defaultChartset));
        if(result != null){
            Set<T> set = Sets.newHashSet();
            for(byte[] buff : result){
                set.add((T)unSerializableObject(buff));
            }
            return set;
        }
        return null;
    }

    public <T> Set<T> getSet2Array(String key, Class<T> cls, boolean bClearData) throws Exception {
        Set<T> result = null;
        ReentrantLock lock = new ReentrantLock();
        try{
            lock.lock();
            result = getSet2Array(key, cls);
            if(bClearData)
                cluster.del(key.getBytes(defaultChartset));
        } finally {
            lock.unlock();
        }
        return result;
    }

    /**
     * 抢单
     * @param provinceCode
     * @param cityCode
     * @param orderId
     * @return
     * @throws Exception
     */
    public int fetchOrder(String provinceCode, String cityCode, Long orderId) throws Exception{
        ReentrantLock lock = new ReentrantLock();
        int code = 0;
        try{
            lock.lock();
            String key = String.format(CacheConstant.COLLECTION_ORDER_CITY_KEY, cityCode);
            //判断是否该省市下的订单，是就将它移除
            if(isExistInSet(key, orderId)){
                //先移除城市下的索引
                removeObjectInSet(key, orderId);
                //取消移除订单的详情，供其他调用
//                removeObject(String.format(CacheConstant.COLLECTION_ORDER_KEY, orderId));
                code = 1;
            }
        } finally {
            lock.unlock();
        }
        return code;
    }


    /**
     * 队列提出
     * @param queueName 	队列名
     * @return             	返回消息体
     */
    public String popMsg(String queueName){
        return cluster.rpop(queueName);
    }

}
