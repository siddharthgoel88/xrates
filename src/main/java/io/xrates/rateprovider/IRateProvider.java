package io.xrates.rateprovider;

import io.xrates.constants.Currency;
import io.xrates.constants.RateProvider;

/*
 * This interface provides skeleton for rate
 * providers of different banks
 * v0.1 - specific to INR and SGD, will be made currency independent
 *  
 * */
public interface IRateProvider {
	public double convert(Currency baseCurrency, Currency targetCurrency);
	public RateProvider getRateProvider();
}
