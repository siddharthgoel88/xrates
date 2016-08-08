package io.xrates.backend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;

import io.xrates.backend.datamodel.dao.ServiceDao;
import io.xrates.backend.rateprovider.util.CurrencyAdapter;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	ServiceDao serviceDao;
	
	@Override
	public List<Currency> getAllAvailableFromCurrency() {
		List<String> fromCurrencies = serviceDao.findAllFromCurrencies();
		List<Currency> fromCurrenciesClass = new ArrayList<>();
		for (String currencyCode : fromCurrencies) {
			Currency newCurr = CurrencyAdapter.getInstanceByCode(currencyCode);
			if(newCurr != null) {
				fromCurrenciesClass.add(newCurr);
			}
		}
		return fromCurrenciesClass;
	}

	@Override
	public List<Currency> getAllAvaialbleToCurrency() {
		List<String> toCurrencies = serviceDao.findAllToCurrencies();
		List<Currency> toCurrenciesClass = new ArrayList<>();
		for (String currencyCode : toCurrencies) {
			Currency newCurr = CurrencyAdapter.getInstanceByCode(currencyCode);
			if(newCurr != null) {
				toCurrenciesClass.add(newCurr);
			}
		}
		return toCurrenciesClass;
	}

}
