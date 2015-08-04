package io.xrates.rateprovider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import io.xrates.Rates;
import io.xrates.constants.Currency;

public class OCBCRateProviderImpl extends AbstractRateProvider{
	private String resourceUrl = "https://www.ocbc.com/rates/daily_price_fx.html";
	private Double toSGD = 0.0;
	private Logger log = LoggerFactory.getLogger(OCBCRateProviderImpl.class.getName());
	private Rates rates = getAllRates();
	
	private String fromCurrency = "INR";
	
	
	private void fetchRates() throws IOException{
		log.debug("Fetching URL ... ");
		Document doc = Jsoup.connect(resourceUrl).get();
		Element ratesTable = doc.select(".MsoNormalTable").get(0);
		getRateForInputCurrency(fromCurrency, ratesTable);
	}
	
	
	private void getRateForInputCurrency(String fromCurrency, Element ratesTable){
		int tableLength = ratesTable.select("tr").size();
		
		for (int i = 5; i < tableLength; i++) {
			try{
				Element tableRow = ratesTable.select("tr").get(i);
				Elements tableData = tableRow.select("td");
				String unit = tableData.get(1).text();
				int unitValue = Integer.parseInt(unit.split(" ")[0]);
				String unitCurrency = unit.split(" ")[1];
				float sellingRate = Float.parseFloat(tableData.get(3).text());
				if(unitCurrency.equals(fromCurrency)){
					float toSgd = sellingRate/unitValue;
					float toInr = unitValue/sellingRate;				
					
					rates.setConversion(Currency.SGD, Currency.INR, toInr);
					rates.setConversion(Currency.INR, Currency.SGD, toSgd);
					rates.setConversion(Currency.INR, Currency.INR, 1.0);
					rates.setConversion(Currency.SGD, Currency.SGD, 1.0);
					
				}
			}catch(IndexOutOfBoundsException e){
				
			}catch(NumberFormatException e){
				
			}

		}
	}
	
	
	@Override
	protected void updateRates() {
		updateCurrencyList();
		try {
			fetchRates();
		} catch (IOException e) {
//			TODO: Implement for specific Exceptions
			System.err.println("There was an error fetching the currency rates");
		}
	}
	
	private void updateCurrencyList() {
		rates.addAvailableCurrency(Currency.INR);
		rates.addAvailableCurrency(Currency.SGD);
	}
	
	
	
	
	
}
