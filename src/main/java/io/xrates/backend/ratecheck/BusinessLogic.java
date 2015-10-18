package io.xrates.backend.ratecheck;

import io.xrates.backend.constants.Constants;
import io.xrates.backend.datamodel.beans.Conversion;
import io.xrates.backend.datamodel.dao.ConversionDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessLogic {
	
	@Autowired
	private ConversionDao conversionDao;
	
	private final double  TRIGGER = 1.0; 
	
	public void process() {
		List<Conversion> unprocessedRates = conversionDao.findAllUnProcessed();
		List<Conversion> processedRates = new ArrayList<Conversion>();
		List<Conversion> notifyAlert = new ArrayList<Conversion>();
		
		for (Conversion currentRate: unprocessedRates) {
			Conversion closingRate = conversionDao.findLastClosingRate(currentRate);
			double change = checkChange(closingRate, currentRate);
			currentRate.setPercentChange(change);
			if (Math.abs(change) >= TRIGGER) {
				currentRate.setStatus(Constants.NOTIFY_ALERT);
			} else {
				currentRate.setStatus(Constants.PROCESSED);
			}
			conversionDao.update(currentRate);
		}
		
	}

	private double checkChange(Conversion closingRate, Conversion currentRate) {
		if (closingRate == null || closingRate.getConversionRate() == 0) {
			return 0;
		}
		
		double closing = closingRate.getConversionRate();
		double current = currentRate.getConversionRate();
		return ( (current - closing) * 100 ) / closing;
		
	}
	
}
