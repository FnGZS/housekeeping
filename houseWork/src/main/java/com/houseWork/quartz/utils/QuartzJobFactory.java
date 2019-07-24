package com.houseWork.quartz.utils;

import com.houseWork.quartz.domin.QuartzJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * QuartzJob工厂
 *
 * @author zjw
 * 2019/7/5 9.50
 */
@PersistJobDataAfterExecution
public class QuartzJobFactory implements Job {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        QuartzJob scheduleJob = (QuartzJob) context.getMergedJobDataMap().get("scheduleJob");
        Class<?> clazz;
//        try {
//            clazz = Class.forName(scheduleJob.getBeanName());
//            String className = clazz.getSimpleName();
//            Object bean = (Object)WebContextListener.getBean(className);
//            Method method = ReflectionUtils.findMethod(bean.getClass(), scheduleJob.getMethodName());
//            ReflectionUtils.invokeMethod(method, bean);
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        logger.info("----------定时开始----------");
    }

}
