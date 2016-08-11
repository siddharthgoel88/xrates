package io.xrates.backend.rateprovider.util;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyAdapter {
	private static Map<String, Currency> mapByName = null;
	private static Map<String, Currency> mapByCode = null;
	private static Logger log = LoggerFactory.getLogger(CurrencyAdapter.class.getName());
	
	public static Currency getInstance(String currencyName) {
		if (mapByName == null) {
			mapByName = new HashMap<String, Currency>();
			Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
			for (Currency cur : availableCurrencies) {
				mapByName.put(cur.getDisplayName(), cur);
			}
			//Adding exceptions
			addExceptions();
		}
		
		if (mapByName.containsKey(currencyName)) {
			return mapByName.get(currencyName);
		} else {
			return null;
		}
	}

	public static Currency getInstanceByCode(String currencyCode) {
//		if (mapByCode == null) {
//			mapByCode = new HashMap<String, Currency>();
//			Set<Currency> avaiableCurrencies = Currency.getAvailableCurrencies();
//			for(Currency curr : avaiableCurrencies) {
//				mapByCode.put(curr.getCurrencyCode(), curr);
//			}
//		}
//		if (mapByCode.containsKey(currencyCode)) {
//			return mapByCode.get(currencyCode);
//		} else {
//			return null;
//		}
		try {
			return Currency.getInstance(currencyCode);
		} catch (IllegalArgumentException e) {
			log.info("Currency code {} a supported ISO 4217 code", currencyCode);
			return null;
		}
	}
	private static void addExceptions() {
		mapByName.put("Sterling Pound", Currency.getInstance("GBP"));
		mapByName.put("Danish Kroner", Currency.getInstance("DKK"));
		mapByName.put("Norwegian Kroner", Currency.getInstance("NOK"));
		mapByName.put("Swedish Kroner", Currency.getInstance("SEK"));
		mapByName.put("Chinese Renminbi(Offshore)", Currency.getInstance("CNY"));
		mapByName.put("Chinese Renminbi", null); //No ISO code support for CNH and RMB
		mapByName.put("Korean Won", Currency.getInstance("KRW"));
		mapByName.put("Sri Lanka Rupee", Currency.getInstance("LKR"));
		mapByName.put("Saudi Rial", Currency.getInstance("SAR"));
	}
}
