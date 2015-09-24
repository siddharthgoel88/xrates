package io.xrates.backend.datamodel.dao;

import io.xrates.backend.datamodel.beans.Provider;
import io.xrates.backend.datamodel.beans.Service;

public interface ServiceDao extends Dao<Service> {
	
	public Service findService(String fromCurrency, String toCurrency, Provider provider);
}
