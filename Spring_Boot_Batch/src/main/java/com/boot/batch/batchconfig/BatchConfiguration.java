package com.boot.batch.batchconfig;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Bean 설정등 spring 환경설정
@Configuration
//배치 사용환경 설정 : 사용자는 컨텍스트에서 Bean으로  Datasource를 제공해야하며 그렇지 않으면 BatchConfigurer를 구성 클래스 자체에 구현해야한다.
@EnableBatchProcessing 
public class BatchConfiguration {
	
	@Bean
	public ResourcelessTransactionManager transactionManager() {
		//MapJobRepositoryFactoryBean 주입하기 위한 필수 Bean
		return new ResourcelessTransactionManager();
	}
	//비 영구적인 메모리 DAO구현을 사용하여 SimpleJobRepository의 생성을 자동화하는 FactoryBean
	@Bean
	public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager txManager) throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);
		
		factory.afterPropertiesSet();
		
		return factory;
	}
	
	//데이터소스 사용 저장소 설정
	@Bean
	public JobRepository jobRepository(MapJobRepositoryFactoryBean factory) throws Exception {
		return factory.getObject();
	}
	
	//jobLauncher를 사용하기위한 Bean
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		
		return launcher;
	}
}
