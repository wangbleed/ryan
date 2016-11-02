package com.ryan.commons.cache;

import com.ryan.commons.util.io.ConnectionConfig;
import redis.clients.jedis.JedisPoolConfig;

import javax.validation.constraints.NotNull;

/**
 * Created by ryan on 15/5/18.
 */
public class RedisConfig implements ConnectionConfig {

    @NotNull
    private Integer maxActive;
    @NotNull
    private Integer maxIdle;
    @NotNull
    private Long maxWait;
    @NotNull
    private Integer cacheShortTime = 10;
    @NotNull
    private Integer cacheForever = 60 * 60 * 24 * 365 * 10;
    @NotNull
    private Integer cacheDeadlineTime;
    @NotNull
    private Integer cacheSessionDeadLineTime;
    @NotNull
    private Integer cacheResoureDeadlintTime;
    @NotNull
    private Boolean cacheSession;
    @NotNull
    private Boolean testOnBorrow;
    @NotNull
    private Boolean testOnReturn;

    private JedisPoolConfig jedisPoolConfig;

    public void init(){
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);

        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);

    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public Integer getCacheSessionDeadLineTime() {
        return cacheSessionDeadLineTime;
    }

    public void setCacheSessionDeadLineTime(Integer cacheSessionDeadLineTime) {
        this.cacheSessionDeadLineTime = cacheSessionDeadLineTime;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getCacheDeadlineTime() {
        return cacheDeadlineTime;
    }

    public void setCacheDeadlineTime(Integer cacheDeadlineTime) {
        this.cacheDeadlineTime = cacheDeadlineTime;
    }

    public Boolean getCacheSession() {
        return cacheSession;
    }

    public void setCacheSession(Boolean cacheSession) {
        this.cacheSession = cacheSession;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Integer getCacheResoureDeadlintTime() {
        return cacheResoureDeadlintTime;
    }

    public void setCacheResoureDeadlintTime(Integer cacheResoureDeadlintTime) {
        this.cacheResoureDeadlintTime = cacheResoureDeadlintTime;
    }

    public Integer getCacheShortTime() {
        return cacheShortTime;
    }

    public void setCacheShortTime(Integer cacheShortTime) {
        this.cacheShortTime = cacheShortTime;
    }

    public Integer getCacheForever() {
        return cacheForever;
    }

    public void setCacheForever(Integer cacheForever) {
        this.cacheForever = cacheForever;
    }
}
