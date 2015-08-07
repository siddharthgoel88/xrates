package io.xrates;

import java.io.IOException;
import java.util.Currency;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.xrates.rateprovider.IRateProvider;

@Component
public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	IRateProvider dbsrpObj;
	List<IRateProvider> dbsrpObjL = null;
	
	//Method based dependency injection
	@Autowired
	public void setRateProvider(List<IRateProvider> rateProviderList){
		this.dbsrpObjL = rateProviderList;
	}
	
	@Scheduled(fixedRate = 10000)
	public void getRate() throws IOException {
		log.debug("Running scheduler");
		for (int i = 0;i<this.dbsrpObjL.size();i++){
			log.info("For 1 "+Currency.getInstance("INR").getDisplayName() +" you get "+String.valueOf(this.dbsrpObjL.get(i).convert(Currency.getInstance("INR"), Currency.getInstance("SGD")))+" "+ Currency.getInstance("SGD").getDisplayName());
		}
	}
}
