package io.xrates;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	IRateProvider dbsrpObj;
	
	@Autowired
	public void setRateProvider(DBSRateProviderImpl dbsRateProvider){
		this.dbsrpObj = dbsRateProvider;
	}
	
	@Scheduled(fixedRate = 4000)
	public void getRate() throws IOException {
		log.debug("Running scheduler");
//		IRateProvider dbsrpObj = new DBSRateProviderImpl();
		log.info("For 1 "+Currency.SGD+" you get "+String.valueOf(this.dbsrpObj.convert(Currency.SGD, Currency.INR))+" "+Currency.INR);
	}
	
	
}
