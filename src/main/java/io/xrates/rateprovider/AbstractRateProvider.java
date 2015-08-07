package io.xrates.rateprovider;

import io.xrates.Rates;
import io.xrates.constants.Currency;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates rates = new Rates(); 
		
	public double convert(Currency from, Currency to) {
		updateRates();
		return rates.getConversion(from, to);
	}
	
	public Rates getRates() {
		return rates;
	}
	
	protected abstract void updateRates();

}
