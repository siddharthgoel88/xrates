package io.xrates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rates {
	private Currency baseCurrency = null;
	private List<Currency> availableCurrencies = null;
	private Map<Currency, Double> rate = new HashMap<Currency, Double>();

	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
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

	public Double getRate(Currency cur) {
		return rate.get(cur);
	}

	public void setRate(Currency cur, Double currentRate) {
		rate.put(cur, currentRate);
	}
}
