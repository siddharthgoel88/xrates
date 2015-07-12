/*
 * This class is imported here for reference from the first version
 * of this project built on Java EE
 * */
package io.xrates;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DBSRateProvider {

	private double toINR;
	private double toSGD;
	private final String resourceURL = "http://www.dbs.com.sg/personal/rates-online/foreign-currency-foreign-exchange.page";
	
	@Scheduled(fixedRate = 100)
	private void updateCurrentRates() throws IOException {
		System.out.println("Starting to fetch url");
		Document doc = Jsoup.connect(resourceURL).get();
		Element rateTable = doc.select("div.rates-table").get(1);
		Element indianTableRow = rateTable.select("table tbody").get(1).select("tr").get(3);
		double inr = Double.parseDouble(indianTableRow.select("td").get(1).text());
		double equivalentsgd = Double.parseDouble(indianTableRow.select("td").get(2).text());
		System.out.println("For " + inr + " rupees you get " + equivalentsgd + " S$.");
		toSGD = equivalentsgd/inr;
		toINR = (1.0)/toSGD;
	}

	public double sgd2inr() {
		try {
			updateCurrentRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toINR;
	}

	public double inr2sgd() {
		try {
			updateCurrentRates();
		} catch(IOException io) {
			System.err.println("Some io error:" + io);
		}
		return toSGD;
	}
	
//	public static void main(String[] args) {
//		RateProvider obj = new DBSRateProviderImpl();
//		System.out.println(obj.sgd2inr());
//		System.out.println(obj.inr2sgd());
//	}

}
