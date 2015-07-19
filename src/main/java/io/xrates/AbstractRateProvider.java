package io.xrates;

public abstract class AbstractRateProvider implements IRateProvider {
	private Rates allRates = new Rates(); 
		
	public double convert(Currency from, Currency to) {
		updateRates();
		if (allRates.isCurrencySupported(from) && allRates.isCurrencySupported(to)) {
			return allRates.getRate(to)/allRates.getRate(from);
		}
		return -1;
	}
	
	public Rates getAllRates() {
		return allRates;
	}
	
	protected abstract void updateRates();

}
