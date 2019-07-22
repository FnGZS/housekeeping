package com.houseWork.quartz.utils;

import com.houseWork.entity.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class QuartzJobManage {

    private static final String JOB_NAME = "MYJOB_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void buildJob(QuartzJob quartzJob) {
        try {
            //创建一个jobDetail的实例，将该实例与HelloJob Class绑定
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).withIdentity(JOB_NAME + quartzJob.getId()).build();
            //创建一个Trigger触发器的实例
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();

            trigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            //重置启动时间
            ((CronTriggerImpl) trigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
        }
    }


}
