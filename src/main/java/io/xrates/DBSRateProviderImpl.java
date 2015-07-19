package io.xrates;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * This is an implementation of RateProvider interface
 * implementing the currency conversion method for bank
 * DBS
 * */
public class DBSRateProviderImpl extends AbstractRateProvider {
	private double toINR=0.0;
	private double toSGD=0.0;
	private String resourceURL = "http://www.dbs.com.sg/personal/rates-online/foreign-currency-foreign-exchange.page";
	private Logger log = LoggerFactory.getLogger(DBSRateProviderImpl.class.getName());
	private Rates rates = getAllRates();
	private String from_curr = "Indian Rupee";
	
	
	/*
	public double sgd2inr() {
		try {
			fetchRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toINR;
		
	}

	public double inr2sgd() {
		try {
			fetchRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toSGD;

	}
	*/

	private void fetchRates() throws IOException {
			System.out.println("Starting to fetch url");
			Document doc = Jsoup.connect(resourceURL).get();
			Element rateTableDiv = doc.select("div.rates-table").get(1);
			Element rateTable = rateTableDiv.select("table tbody").get(1);
			getRateForInputCurr(from_curr, rateTable);
	}
	
	private void getRateForInputCurr(String from_curr, Element rateTable){
		int length = rateTable.select("tr").size();	//will be used to loop through to parse entire table
		double targetCurr = 0.0;
		double equivalentsgd = 0.0;
		for (int i = 0;i<length;i++){
			Element currTableRow = rateTable.select("tr").get(i);
			String curr = currTableRow.select("td").get(0).text();
			String cleanedCurr = curr.replace("\u00a0","");
			String enumInput  = "Indian Rupee";	//This will the currency selected from enum
			if(cleanedCurr.equals(enumInput)){
				targetCurr = Double.parseDouble(currTableRow.select("td").get(1).text());
				equivalentsgd = Double.parseDouble(currTableRow.select("td").get(2).text());
				//System.out.println("For " + targetCurr + " rupees you get " + equivalentsgd + " S$.");
				toSGD = equivalentsgd/targetCurr;
				toINR = (1.0)/toSGD;
				rates.setRate(Currency.INR, toINR);
				rates.setRate(Currency.SGD, 1.0);
				return;
			}
		}
		
//		double inr = Double.parseDouble(indianTableRow.select("td").get(1).text());
		
		
	}

	@Override
	protected void updateRates() {
		log.debug("Inside updateRates of DBSRateProviderImpl");
		rates.setBaseCurrency(Currency.SGD);
		updateListOfCurrencies();
		try {
			fetchRates();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * TODO: Need to crawl page and add all currencies.
	 * Currently hardcoded it.
	 */
	private void updateListOfCurrencies() {
		rates.addAvailableCurrency(Currency.INR);
		rates.addAvailableCurrency(Currency.SGD);
	}

}
