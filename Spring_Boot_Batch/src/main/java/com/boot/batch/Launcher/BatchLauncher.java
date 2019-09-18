package com.boot.batch.Launcher;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.boot.batch.dto.BatchDto;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchLauncher {
	private static String BATCH_NAME = "BaseJobStep";
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Scheduled(cron="1 * * * * *")
	public void scheduler() throws Exception {
		String jobId = String.valueOf(System.currentTimeMillis());
		
		System.out.println("Start jobId : " + jobId);
		
		JobParameters param = new JobParametersBuilder().addString("jobId", jobId).toJobParameters();
		
		JobExecution execution = jobLauncher.run(baseJob(), param);
		
		System.out.println("end : " + param.getString("jobId") + ":::" + execution.getStatus());
	}
	
	@Bean
	public Job baseJob() {
		return jobBuilderFactory.get("[JOB - " + BATCH_NAME + "]").start(baseStep()).build();
	}
	
	@Bean
	public Step baseStep() {
		return stepBuilderFactory.get("[STEP - " + BATCH_NAME + "]").<BatchDto, BatchDto>chunk(20).reader(sampleItemReader())
				.processor(sampleItemProcessor()).writer(sampleItemWriter()).build();
	}
	
	@Bean
	public ItemReader<BatchDto> sampleItemReader(){
		MyBatisPagingItemReader<BatchDto>  reader = new MyBatisPagingItemReader<BatchDto>();
		reader.setSqlSessionFactory(sqlSessionFactory);
		reader.setQueryId("select");
		return reader;
	}
	
	@Bean
	public ItemProcessor<BatchDto, BatchDto> sampleItemProcessor(){
		return new ItemProcessor<BatchDto, BatchDto>() {

			@Override
			public BatchDto process(BatchDto item) throws Exception {
				return item;
			}
		};
	}
	
	@Bean
	public ItemWriter<BatchDto> sampleItemWriter(){
		ItemWriter<BatchDto> writer = new ItemWriter<BatchDto>() {
			
			@Override
			public void write(List<? extends BatchDto> items) throws Exception {
				for(BatchDto msg : items) {
					System.out.println("the data : " + msg.getPassword());
				}
			}
		};
		
		return writer;
	}
}
