package com.ryan.commons.service;

/**
 * Created by ryan on 15/5/15.
 * 基础启动服务
 */
public interface IService extends Runnable {

    /**
     * 初始化
     */
    public void init();

    /**
     * 启动当前服务
     */
    public void start();

    /**
     * 停止当前服务
     */
    public void stop();

    /**
     * 要进行注册的服务对象
     *
     * @return
     */
    public RegServiceVo getRegServiceVo();

    /**
     * 启动服务前执行操作
     *
     */
    public void beforeStart();

    /**
     * 启动服务后执行的操作
     */
    public void afterStart();

    /**
     * 停止服务前先执行的操作
     */
    public void beforeStop();

    /**
     * 停止服务后再执行的操作
     */
    public void afterStop();

}
