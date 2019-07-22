package com.houseWork.quartz.config;

import com.houseWork.entity.QuartzJob;
import com.houseWork.mapper.quartz.QuartzDao;
import com.houseWork.quartz.utils.QuartzJobManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuartzScheduler implements ApplicationRunner {

    @Autowired
    private QuartzDao quartzDao;

    @Autowired
    private QuartzJobManage quartzJobManage;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobList = quartzDao.getJob();
        quartzJobList.forEach(quartzJob -> {
            quartzJobManage.buildJob(quartzJob);
        });
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}