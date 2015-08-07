package io.xrates;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.xrates.constants.RateProvider;

public class Rates {
	private RateProvider rateProvider;
	private List<Currency> availableCurrencies = new ArrayList<Currency>();
	private Map<String, Map<String, Double>> rates = new HashMap<String, Map<String,Double>>();
	private Date updateTime;
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

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

	public Map<String, Map<String, Double>> getRates() {
		return rates;
	}
	
	public void setConversion(Currency from, Currency to, Double rate) {
		if (rates.containsKey(from.getCurrencyCode())) {
			Map<String, Double> map = rates.get(from.getCurrencyCode());
			map.put(to.getCurrencyCode(), rate);
		} else {
			Map<String, Double> map = new HashMap<String, Double>();
			map.put(to.getCurrencyCode(), rate);
			rates.put(from.getCurrencyCode(), map);
		}
	}
	
	public double getConversion(Currency from, Currency to) {
		if (rates.containsKey(from.getCurrencyCode())) {
			Map<String, Double> map = rates.get(from.getCurrencyCode());
			if (map.containsKey(to.getCurrencyCode())) {
				return map.get(to.getCurrencyCode());
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
