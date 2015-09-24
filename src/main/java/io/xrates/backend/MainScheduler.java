package io.xrates.backend;

import java.io.IOException;
import java.util.Currency;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.xrates.backend.exceptions.RateProviderException;
import io.xrates.backend.rateprovider.IRateProvider;

@Component
public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	
	@Autowired
	private List<IRateProvider> rateProviders;
	
	@Scheduled(fixedRate = 10000)
	public void getRate() throws IOException {
		log.debug("Running scheduler");
		for (int i = 0;i<this.rateProviders.size();i++){
			try {
				log.info(rateProviders.get(i).getRateProvider().getProviderName() + 
						" : For 1 "+Currency.getInstance("SGD").getDisplayName() +
						" you get " + String.valueOf( 
							this.rateProviders.get(i).convert(Currency.getInstance("SGD"), 
							Currency.getInstance("INR"))) 
							+ " " + Currency.getInstance("INR").getDisplayName());
			} catch (RateProviderException e) {
				log.error("Error in " + rateProviders.getClass() + " :" + e.getMessage());
			}
		}
	}
}
