/*
 * This is an implementation of RateProvider interface
 * implementing the currency conversion method for bank
 * DBS
 * */

package io.xrates.rateprovider;
import java.io.IOException;
import java.util.Currency;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.xrates.Rates;
import io.xrates.constants.RateProvider;

public class DBSRateProviderImpl extends AbstractRateProvider {
	private double toINR=0.0;
	private double toSGD=0.0;
	private String resourceURL = "http://www.dbs.com.sg/personal/rates-online/foreign-currency-foreign-exchange.page";
	private Logger log = LoggerFactory.getLogger(DBSRateProviderImpl.class.getName());
	private Rates rates = getRates();
	private String from_curr = "Indian Rupee";
	
	public DBSRateProviderImpl() {
		setRateProvider(RateProvider.DBS);
	}
	
	@Override
	protected void updateRates() {
		log.debug("Inside updateRates of DBSRateProviderImpl");
		updateListOfCurrencies();
		try {
			fetchRates();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fetchRates() throws IOException {
			log.info("DBS: Fetching page @ " + resourceURL);
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
				
				rates.setConversion(Currency.getInstance("SGD"), Currency.getInstance("INR"), toINR);
				rates.setConversion(Currency.getInstance("INR"), Currency.getInstance("SGD"), 1/toINR);
				return;
			}
		}
	}

	/*
	 * TODO: Need to crawl page and add all currencies.
	 * Currently hard-coded it.
	 */
	private void updateListOfCurrencies() {
		rates.addAvailableCurrency(Currency.getInstance("INR"));
		rates.addAvailableCurrency(Currency.getInstance("SGD"));
	}

}
