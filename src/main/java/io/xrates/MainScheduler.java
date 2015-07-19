package io.xrates;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	@Scheduled(fixedRate = 5000)
	public void getRate() throws IOException {
		log.debug("Running scheduler");
		
		IRateProvider dbsrpObj = new DBSRateProviderImpl();
		log.info(String.valueOf(dbsrpObj.convert(Currency.SGD, Currency.INR)));
	}

}
