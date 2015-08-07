package io.xrates.rateprovider;

import java.util.Currency;

import io.xrates.constants.RateProvider;

/*
 * This interface provides skeleton for rate
 * providers of different exchange rate providers
 * 
 * */
public interface IRateProvider {
	public double convert(Currency baseCurrency, Currency targetCurrency);
	public RateProvider getRateProvider();
}
