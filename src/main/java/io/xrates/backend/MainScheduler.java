package io.xrates.backend;

import io.xrates.backend.datamodel.dao.UserDao;
import io.xrates.backend.ratecheck.BusinessLogic;
import io.xrates.backend.rateprovider.IRateProvider;

import java.io.IOException;
import java.util.Currency;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainScheduler {
	
	Logger log = LoggerFactory.getLogger(MainScheduler.class.getName());
	
	@Autowired
	private List<IRateProvider> rateProviders;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BusinessLogic bl;
	
	@Scheduled(fixedRate = 10000)
	@Transactional
	public void getRate() throws IOException {
		log.debug("Running scheduler");
		
		for (int i = 0;i<this.rateProviders.size();i++){
				log.info(rateProviders.get(i).getRateProvider().getProviderName() + 
						" : For 1 "+Currency.getInstance("SGD").getDisplayName() +
						" you get " + String.valueOf( 
							this.rateProviders.get(i).convert(Currency.getInstance("SGD"), 
							Currency.getInstance("INR"))) 
							+ " " + Currency.getInstance("INR").getDisplayName());
		}
		
		bl.process();
		bl.notifyAlerts();
	}
}
