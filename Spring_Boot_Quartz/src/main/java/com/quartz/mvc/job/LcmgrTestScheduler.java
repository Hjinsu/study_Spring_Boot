package com.quartz.mvc.job;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class LcmgrTestScheduler {
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	
	//해당 클래스가 인스턴스화 되자마자 자동으로 동작하게 하려는 메소드
	@PostConstruct
	public void start() throws SchedulerException {
		schedulerFactory = new StdSchedulerFactory();
		scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		
		//job 지정
		JobDetail job = JobBuilder.newJob(LcmgrTestJob.class).withIdentity("testJob").build();
		//trigger 생성
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("20 * * * * ?")).build();
		
		// startAt과 endAt를 사용해 job  스케줄의 시작, 종료 시간도 지정 가능
		// Trigger trigger = TriggerBuilder.newTrigger().startAt(startDateTime).endAt(EndDateTime)
        //  .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * *")).build();
		
		scheduler.scheduleJob(job,trigger);
		
	}
}
