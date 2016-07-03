package io.xrates.backend.services;

import java.util.List;

public interface ServicesService {
	public List<String> getAllAvailableFromCurrency();
	public List<String> getAllAvaialbleToCurrency();
}
