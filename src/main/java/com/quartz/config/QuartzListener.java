package com.quartz.config;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.TestJob;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import com.quartz.*;

@WebListener
public class QuartzListener extends QuartzInitializerListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple").withSchedule(
                    CronScheduleBuilder.cronSchedule("0/5 0/5 0 ? * * *")).startNow().build();
            // every 5 mins
            
            scheduler.getListenerManager().addJobListener(new JobExecutionListener());
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            
//            scheduler.shutdown(true);
        } catch (Exception e) {
            ctx.log("There was an error scheduling the job.", e);
        }
    }

}