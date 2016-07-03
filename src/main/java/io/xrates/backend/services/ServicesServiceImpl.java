package io.xrates.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.xrates.backend.datamodel.dao.ServiceDao;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	ServiceDao serviceDao;
	
	@Override
	public List<String> getAllAvailableFromCurrency() {
		return serviceDao.findAllFromCurrencies();
	}

	@Override
	public List<String> getAllAvaialbleToCurrency() {
		return serviceDao.findAllToCurrencies();
	}

}
