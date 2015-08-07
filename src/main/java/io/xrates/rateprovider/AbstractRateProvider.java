package io.xrates.rateprovider;

import io.xrates.Rates;
import io.xrates.constants.Currency;
import io.xrates.constants.RateProvider;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates rates = new Rates(); 
	private RateProvider rateProvider;
		
	public double convert(Currency from, Currency to) {
		updateRates();
		return rates.getConversion(from, to);
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
	
	protected abstract void updateRates();

}
