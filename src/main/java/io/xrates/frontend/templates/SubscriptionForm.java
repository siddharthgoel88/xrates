package io.xrates.frontend.templates;

import java.util.Set;
import java.util.Currency;

public class SubscriptionForm {
	
	private static String[] currencyList = null;
	private String fromCurrency = "type from currency code";
	private String toCurrency= "type from currency code";
	private String selectedCurrency = "SGD";
	
	static{
		int index = 0;
		Set<Currency> currencySet = Currency.getAvailableCurrencies();
		currencyList = new String[currencySet.size()];
		for (Currency currency : currencySet) {
			currencyList[index++] = currency.getCurrencyCode();
			System.out.println("currencyList: " + currencyList[index-1] );
		}
	}
	private long id = 999L;
	
	
	public String[] getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(String[] currencyList) {
		SubscriptionForm.currencyList = currencyList;
	}
	public String getSelectedCurrency() {
		return selectedCurrency;
	}
	public void setSelectedCurrency(String selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
	}
	
	public String getCurrency(){
		return selectedCurrency;
	}
	public void setCurrency(String curr){
		this.selectedCurrency = curr;
	}
	
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getFromCurrency(){
		return fromCurrency;
	}
	
	public void setFromCurrency(String fromCurrency){
		this.fromCurrency= fromCurrency;
	}
	
	public String getToCurrency(){
		return toCurrency;
	}
	
	public void setToCurrency(String toCurrency){
		this.toCurrency= toCurrency;
	}
}
