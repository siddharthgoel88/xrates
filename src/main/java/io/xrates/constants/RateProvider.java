package io.xrates.constants;

public enum RateProvider {
	DBS(1, "DBS"),
	OCBC(2, "OCBC"),
	REMIT(3, "REMIT GURU");
	
	private final int value;
	private final String providerName;
	
	private RateProvider(int value, String providerName) {
		this.value = value;
		this.providerName = providerName;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getProviderName() {
		return providerName;
	}
}
