package com.luxury.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration 
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

	@Override 
    public void configureTasks(ScheduledTaskRegistrar registrar) { 
        registrar.setTaskScheduler(taskScheduler()); 
    } 
 
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() { 
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); 
        executor.setMaxPoolSize(10); 
        executor.setCorePoolSize(5); 
        executor.setQueueCapacity(30); 
        executor.setAwaitTerminationSeconds(420);
 
        return executor; 
    } 
 
    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() { 
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler(); 
        scheduler.setPoolSize(10); 
        scheduler.setAwaitTerminationSeconds(420);
        
        return scheduler; 
    } 
}
