package com.ryan.commons.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

/**
 * Created by xudongwang on 2016/7/15.
 */
public class JobDetailExt {

    private JobDetail jobDetail;
    private CronTrigger cronTrigger;

    public JobDetailExt(){}

    public JobDetailExt(JobDetail jobDetail, CronTrigger cronTrigger) {
        this.jobDetail = jobDetail;
        this.cronTrigger = cronTrigger;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public CronTrigger getCronTrigger() {
        return cronTrigger;
    }

    public void setCronTrigger(CronTrigger cronTrigger) {
        this.cronTrigger = cronTrigger;
    }
}
