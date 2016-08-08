package io.xrates.backend.services;

import java.util.Currency;
import java.util.List;

public interface ServicesService {
	public List<Currency> getAllAvailableFromCurrency();
	public List<Currency> getAllAvaialbleToCurrency();
}
