package io.xrates.backend.rateprovider;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lalyos.jfiglet.FigletFont;

import io.xrates.backend.Rates;
import io.xrates.backend.constants.RateProvider;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates rates = new Rates(); 
	private RateProvider rateProvider;
	private long lastUpdated = -1;
	private long stalenessTime = 300000; //Data which is stale up to 5 minutes is fine
	private Logger log = LoggerFactory.getLogger(AbstractRateProvider.class.getName());
		
	public double convert(Currency from, Currency to) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastUpdated > stalenessTime) {
			updateRates();
			lastUpdated = currentTime;
		}
		return rates.getConversion(from, to);
	}
	
	public RateProvider getRateProvider() {
		return rateProvider;
	}
	
	protected void setRateProvider(RateProvider rateProvider) {
		log.info("Instantiating following service : \n" + FigletFont.convertOneLine(rateProvider.getProviderName()));
		this.rateProvider = rateProvider;
	}
	
	public Rates getRates() {
		return rates;
	}
	
	protected abstract void updateRates();

}
