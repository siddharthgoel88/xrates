package io.xrates;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates allRates = null; 
	
	protected abstract void updateRates();
	
	public abstract double convert(Currency from, Currency to);
	
	public Rates getAllRates() {
		return allRates;
	}
}
