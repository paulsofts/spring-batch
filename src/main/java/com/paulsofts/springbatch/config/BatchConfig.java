package com.paulsofts.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.paulsofts.springbatch.primary.Employee;
import com.paulsofts.springbatch.secondary.Developer;


@Configuration
public class BatchConfig{
	
	@Autowired
	private Reader reader;
	
	@Autowired
	private Processor processor;
	
	@Autowired
	private Writer writer;
	
    @Bean
	public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		return new JobBuilder("myJob", jobRepository)
				.start(step(jobRepository, platformTransactionManager))
				.build();
	}

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("myStep", jobRepository)
            .<Employee, Developer>chunk(1, platformTransactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .taskExecutor(taskExecutor())
            .build();
    }
    
    public TaskExecutor taskExecutor() {
    	SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
    	//two threads run concurrently for batch processing
    	simpleAsyncTaskExecutor.setConcurrencyLimit(2);
    	return simpleAsyncTaskExecutor;
    }
}
