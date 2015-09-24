package io.xrates.backend.datamodel.dao;

import io.xrates.backend.datamodel.beans.Provider;
import io.xrates.backend.datamodel.beans.Service;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDaoImpl extends AbstractDao<Service> implements ServiceDao {
	
	private Logger log = LoggerFactory.getLogger(ServiceDaoImpl.class.getName());
	
	@Override
	@Transactional
	public Service findService(String fromCurrency, String toCurrency, Provider provider) {
		Query query = entityManager.createQuery("Select service from Service service where "
				+ "service.toCurrency = :toCurrency and service.fromCurrency = :fromCurrency and "
				+ "service.provider = :provider");
		query.setParameter("toCurrency", toCurrency);
		query.setParameter("fromCurrency", fromCurrency);
		query.setParameter("provider", provider);
		
		Service service = null;
		try {
			service = (Service) query.getSingleResult();
		} catch (NoResultException e) {
			log.info("Service from " + fromCurrency + " to "
					+ toCurrency + " for " + provider.getProviderName() +
					" not present. So adding it.");
		}
		
		
		if (service == null) {
			service = new Service();
			service.setFromCurrency(fromCurrency);
			service.setToCurrency(toCurrency);
			service.setProvider(provider);	
			create(service);
		}
		
		return service;
	}
}
