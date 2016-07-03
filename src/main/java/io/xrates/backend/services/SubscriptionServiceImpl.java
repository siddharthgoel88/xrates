package io.xrates.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.xrates.backend.datamodel.dao.ServiceDao;
import io.xrates.frontend.templates.SubscriptionForm;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	private ServiceDao serviceDao;
	
	@Override
	public SubscriptionForm getSubscritionForm() {
		SubscriptionForm subscriptionForm = new SubscriptionForm();
		List<String> availableFromCurrencies = serviceDao.findAllFromCurrencies();
		String[] availableFromCurrenciesArr = availableFromCurrencies.toArray(new String[availableFromCurrencies.size()]);
		subscriptionForm.setCurrencyList(availableFromCurrenciesArr);
		
		return subscriptionForm;
	}

}
