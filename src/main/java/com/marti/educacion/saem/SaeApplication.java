package com.marti.educacion.saem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
public class SaeApplication {

	private static final Logger logger = LoggerFactory.getLogger(SaeApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SaeApplication.class, args);
		
		logger.info("Beans in application");
		
		String beanNames[] = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		
		for(String beanName: beanNames){
			logger.info(beanName);
		}
	}
}
