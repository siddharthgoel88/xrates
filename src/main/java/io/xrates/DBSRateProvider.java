package io.xrates;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/*
 * This is an implementation of RateProvider interface
 * implementing the currency conversion method for bank
 * DBS
 * */
public class DBSRateProvider implements RateProvider {
	private double toINR=0.0;
	private double toSGD=0.0;
	private String resourceURL = "http://www.dbs.com.sg/personal/rates-online/foreign-currency-foreign-exchange.page";
	private FROM_CURRENCY from_curr = FROM_CURRENCY.Indian_Rupee;
	
	@Override
	public double sgd2inr() {
		try {
			fetchRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toINR;
		
	}

	@Override
	public double inr2sqd() {
		try {
			fetchRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toSGD;

	}

	@Override
	public void fetchRates() throws IOException {
			System.out.println("Starting to fetch url");
			Document doc = Jsoup.connect(resourceURL).get();
			Element rateTableDiv = doc.select("div.rates-table").get(1);
			Element rateTable = rateTableDiv.select("table tbody").get(1);
			getRateForInputCurr(from_curr, rateTable);
	}
	
	public void getRateForInputCurr(FROM_CURRENCY from_curr, Element rateTable){
		int length = rateTable.select("tr").size();	//will be used to loop through to parse entire table
		Element indianTableRow = rateTable.select("tr").get(3);
		double inr = Double.parseDouble(indianTableRow.select("td").get(1).text());
		double equivalentsgd = Double.parseDouble(indianTableRow.select("td").get(2).text());
		System.out.println("For " + inr + " rupees you get " + equivalentsgd + " S$.");
		toSGD = equivalentsgd/inr;
		toINR = (1.0)/toSGD;
		return ;
	}
	
	

}
