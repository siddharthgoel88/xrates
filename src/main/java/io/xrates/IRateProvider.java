package io.xrates;

/*
 * This interface provides skeleton for rate
 * providers of different banks
 * v0.1 - specific to INR and SGD, will be made currency independent
 *  
 * */
public interface IRateProvider {
	
	public double sgd2inr();
	public double inr2sgd();
	
}