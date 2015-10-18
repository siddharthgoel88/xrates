package io.xrates.backend.datamodel.dao;

import io.xrates.backend.constants.Constants;
import io.xrates.backend.datamodel.beans.Conversion;
import io.xrates.backend.datamodel.beans.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConversionDaoImpl extends AbstractDao<Conversion> implements ConversionDao {
	
	private Logger log = LoggerFactory.getLogger(SubscriptionDaoImpl.class.getName());
	
	@Autowired
	ServiceDao serviceDao;
	
	@Override
	public List<Conversion> findAllUnProcessed() {
		Query q = entityManager.createQuery("select c from Conversion c "
				+ "where c.status = :status");
		q.setParameter("status", Constants.UNPROCESSED);
		List<Conversion> result = q.getResultList();
		if (result != null) {
			log.info("Found " + result.size() + " records to be processed");
		}
		return result;
	}
	
	@Override
	public Conversion findLastClosingRate(long serviceId) {
		Service service = serviceDao.get(serviceId);
		Query q = entityManager.createQuery("select con from Conversion con "
				+ "where con.conversionTimestamp in "
				+ "( select max(c.conversionTimestamp) from "
				+ "Conversion c where c.conversionTimestamp < :todayTimestamp "
				+ "and c.service = :service ) and con.service = :service ");
		q.setParameter("service", service);
		//TODO:Temporarily hard-coded time-zone. Need to extend data-model to accommodate it.
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		q.setParameter("todayTimestamp", cal.getTime());
		try {
			Conversion result = (Conversion) q.getSingleResult();
			return result;
		} catch (NoResultException e) {
			log.error("Seems there is no previous entry for this service-id => " + serviceId );
			return null;
		}
		
	}
	
	@Override
	public Conversion findLastClosingRate(Conversion currentRate) {
		return findLastClosingRate(currentRate.getService().getServiceId());
	}
}
