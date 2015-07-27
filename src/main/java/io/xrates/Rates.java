package io.xrates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.xrates.constants.Currency;

public class Rates {
	private List<Currency> availableCurrencies = new ArrayList<Currency>();
	private double[][] currencyMatrix = new double[Currency.values().length][Currency.values().length];
	
//	public Currency getBaseCurrency() {
//		return baseCurrency;
//	}
//
//	public void setBaseCurrency(Currency baseCurrency) {
//		this.baseCurrency = baseCurrency;
//	}
	
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

//	public Double getRate(Currency cur) {
//		return rate.get(cur);
//	}
//
//	public void setRate(Currency cur, Double currentRate) {
//		rate.put(cur, currentRate);
//	}
	
	public void setConversion(Currency from, Currency to, double value){
		currencyMatrix[from.ordinal()][to.ordinal()] = value;
		currencyMatrix[to.ordinal()][from.ordinal()] = 1/value;
	}
	
	public double getConversion(Currency from, Currency to){
		return currencyMatrix[from.ordinal()][to.ordinal()];
	}
	
}
