package io.xrates.backend.datamodel.dao;

import java.util.List;

import io.xrates.backend.datamodel.beans.Provider;
import io.xrates.backend.datamodel.beans.Service;

public interface ServiceDao extends Dao<Service> {
	
	public Service findService(String fromCurrency, String toCurrency, Provider provider);
	public List<Provider> findProviders(String fromCurrency, String toCurrency);
	
}
