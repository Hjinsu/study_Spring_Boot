package com.quartz.mvc.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class LcmgrTestJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Job interface를 implements하여 execute 메소드에 로직을 넣는다.
		long curtime = System.currentTimeMillis();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		String datetime = sd.format(new Date(curtime));
		
		System.out.println("-------------job 수행-------------");
		System.out.println(datetime);
		
	}

	
}
