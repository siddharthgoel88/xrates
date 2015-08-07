package io.xrates.rateprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lalyos.jfiglet.FigletFont;

import io.xrates.Rates;
import io.xrates.constants.Currency;
import io.xrates.constants.RateProvider;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates rates = new Rates(); 
	private RateProvider rateProvider;
	private Logger log = LoggerFactory.getLogger(AbstractRateProvider.class.getName());
		
	public double convert(Currency from, Currency to) {
		updateRates();
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
