package io.xrates;

import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainScheduler {
	
	static Logger log = Logger.getLogger(MainScheduler.class.getName());
	
	@Scheduled(fixedRate = 100)
	public void foo() {
		System.out.println("Hello");
		log.info("Jaadu !!");
	}

}
