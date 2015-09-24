package io.xrates.backend.datamodel.dao;

import io.xrates.backend.constants.RateProvider;
import io.xrates.backend.datamodel.beans.Provider;

public interface ProviderDao extends Dao<Provider>{
	
	public Provider findProvider(RateProvider rateProvider);
	
}
