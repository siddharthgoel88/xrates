package io.xrates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.xrates.constants.Currency;
import io.xrates.constants.RateProvider;

public class Rates {
	private RateProvider rateProvider;
	private List<Currency> availableCurrencies = new ArrayList<Currency>();
	private Map<Currency, Map<Currency, Double>> rates = new HashMap<Currency, Map<Currency,Double>>();
	
	public void addAvailableCurrency(Currency cur) {
		availableCurrencies.add(cur);
	}

	public List<Currency> getAvailableCurrencies() {
		return availableCurrencies;
	}

	public void setAvailableCurrencies(List<Currency> availableCurrencies) {
		this.availableCurrencies = availableCurrencies;
	}
	
	public boolean isCurrencySupported(Currency cur) {
		return availableCurrencies.contains(cur);
	}

	public Map<Currency, Map<Currency, Double>> getRates() {
		return rates;
	}
	
	public void setConversion(Currency from, Currency to, Double rate) {
		if (rates.containsKey(from)) {
			Map<Currency, Double> map = rates.get(from);
			map.put(to, rate);
		} else {
			Map<Currency, Double> map = new HashMap<Currency, Double>();
			map.put(to, rate);
			rates.put(from, map);
		}
	}
	
	public double getConversion(Currency from, Currency to) {
		if (rates.containsKey(from)) {
			Map<Currency, Double> map = rates.get(from);
			if (map.containsKey(to)) {
				return map.get(to);
			}
		}
		return -1;
	}

	public RateProvider getRateProvider() {
		return rateProvider;
	}

	public void setRateProvider(RateProvider rateProvider) {
		this.rateProvider = rateProvider;
	}
}
