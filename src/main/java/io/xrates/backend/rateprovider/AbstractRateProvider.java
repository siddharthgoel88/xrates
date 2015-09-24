package io.xrates.backend.rateprovider;

import io.xrates.backend.Rates;
import io.xrates.backend.constants.RateProvider;
import io.xrates.backend.datamodel.util.XratesDBUtil;
import io.xrates.backend.exceptions.RateProviderException;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.lalyos.jfiglet.FigletFont;

public abstract class AbstractRateProvider implements IRateProvider {
	protected Rates rates = null;
	private RateProvider rateProvider = null;
	private long lastUpdated = -1;
	private long stalenessTime = 300000; //Data which is stale up to 5 minutes is fine
	private Logger log = LoggerFactory.getLogger(AbstractRateProvider.class.getName());
	
	@Autowired
	private XratesDBUtil xratesDBUtil;
		
	public double convert(Currency from, Currency to) throws RateProviderException {
		if (rateProvider == null) {
			throw new RateProviderException("RateProvider not set.");
		}
		
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastUpdated > stalenessTime) {
			log.info("Instantiating following service : \n" + 
					FigletFont.convertOneLine(rateProvider.getProviderName()));
			rates = new Rates();
			rates.setRateProvider(rateProvider);
			updateRates();
			xratesDBUtil.persistRates(rates);
			lastUpdated = currentTime;
		}
		return round(rates.getConversion(from, to), 2);
	}
	
	public RateProvider getRateProvider() {
		return rateProvider;
	}
	
	protected void setRateProvider(RateProvider rateProvider) {
		this.rateProvider = rateProvider;
	}
	
	public Rates getRates() {
		return rates;
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	protected abstract void updateRates();

}
