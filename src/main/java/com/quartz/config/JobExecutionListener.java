package com.quartz.config;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobExecutionListener implements JobListener {

   private final Logger log = LoggerFactory.getLogger(JobExecutionListener.class);

   public String getName() {
       return JobExecutionListener.class.getSimpleName();
   }

   public void jobToBeExecuted(JobExecutionContext context) {
       try {
           String jobName = context.getJobDetail().getKey().toString();
           log.info("{} is about to be executed", jobName );
       } catch (Exception e) {
           log.error("Exception before job execution in listener", e);
       }
   }

   public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
       try {
           String jobName = context.getJobDetail().getKey().toString();
           log.info("{} finised execution", jobName);
       } catch (Exception e) {
           log.error("Exception after job execution in listener", e);
       }
   }

   public void jobExecutionVetoed(JobExecutionContext context) {
       try {
           String jobName = context.getJobDetail().getKey().toString();
           log.info("{} was about to be executed but a TriggerListener vetoed it's execution", jobName);
       } catch (Exception e) {
           log.error("Exception during job execution veto in listener", e);
       }
   }
}