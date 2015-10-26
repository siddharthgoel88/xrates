package io.xrates.backend.rateprovider;

import java.util.Currency;

import io.xrates.backend.constants.RateProvider;
import io.xrates.backend.exceptions.RateProviderException;

/*
 * This interface provides skeleton for rate
 * providers of different exchange rate providers
 * 
 * */
public interface IRateProvider {
	public double convert(Currency baseCurrency, Currency targetCurrency) throws RateProviderException;
	public RateProvider getRateProvider();
}
