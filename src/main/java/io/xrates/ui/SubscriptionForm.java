package io.xrates.ui;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Currency;

public class SubscriptionForm {
	
	private long id = 999L;
	private String fromCurrency = "type from currency code";
	private String toCurrency= "type from currency code";
	private String selectedCurrency = "SGD";
	
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
