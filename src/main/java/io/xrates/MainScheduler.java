package io.xrates;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	@Scheduled(fixedRate = 100)
	public void foo() {
		System.out.println("Hello");
		log.info("Jaadu !!");
	}

}
