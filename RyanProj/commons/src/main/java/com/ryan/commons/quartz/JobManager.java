package com.ryan.commons.quartz;

import org.quartz.*;

import javax.annotation.PostConstruct;


/**
 * Created by xudongwang on 2016/7/15.
 */
public abstract class JobManager {

    protected JobDetailExt ctripJobDetail;
    protected Class cls;

    private String cronExp;
    private String desc;

    @PostConstruct
    private void initManager(){
        ctripJobDetail = new JobDetailExt();
        initClass();

        JobKey jobKey = initJobKey();
        JobDetail jobDetail = initJobDetail().withIdentity(jobKey).storeDurably(true).build();
        jobDetail.getJobDataMap().put("lock_key", getRedisLockKey());
        ctripJobDetail.setJobDetail(jobDetail);

        CronTrigger trigger = initTrigger().withIdentity(jobKey.getName(), jobKey.getGroup()).forJob(jobDetail).withDescription(desc).build();
        ctripJobDetail.setCronTrigger(trigger);
    }

    public abstract void initClass();

    public abstract String getRedisLockKey();

    public JobKey initJobKey() {
        String key = cls.getSimpleName();
        return new JobKey(key);
    }

    public abstract JobBuilder initJobDetail();

    public TriggerBuilder<CronTrigger> initTrigger() {
        return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExp));

//        CronTriggerImpl cronTrigger = new CronTriggerImpl();
//        cronTrigger.setName(cls.getSimpleName());
//        try{
//            cronTrigger.setCronExpression(cronExp);
//        } catch (ParseException e){
//            e.printStackTrace();
//        }
//        return cronTrigger;
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public JobDetail getJobDetail(){
        return ctripJobDetail.getJobDetail();
    }

    public Trigger getCronTrigger(){
        return ctripJobDetail.getCronTrigger();
    }


}
