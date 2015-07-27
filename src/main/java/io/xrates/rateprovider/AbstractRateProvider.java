package io.xrates.rateprovider;

import io.xrates.Rates;
import io.xrates.constants.Currency;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates allRates = new Rates(); 
		
	public double convert(Currency from, Currency to) {
		updateRates();
		if (allRates.isCurrencySupported(from) && allRates.isCurrencySupported(to)) {
			return allRates.getConversion(from, to);
		}
		return -1;
	}
	
	public Rates getAllRates() {
		return allRates;
	}
	
	protected abstract void updateRates();

}
