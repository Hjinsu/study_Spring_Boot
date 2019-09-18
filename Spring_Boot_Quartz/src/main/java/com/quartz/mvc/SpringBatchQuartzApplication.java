package com.quartz.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quartz.mvc.job.LcmgrTestScheduler;

@SpringBootApplication
public class SpringBatchQuartzApplication {

	@Autowired
	private LcmgrTestScheduler scheduler;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchQuartzApplication.class, args);
	}

}
