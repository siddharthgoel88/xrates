package io.xrates.backend.datamodel.dao;

import java.util.List;

import io.xrates.backend.datamodel.beans.Conversion;

public interface ConversionDao extends Dao<Conversion>{
	public List<Conversion> findAllUnProcessed();
	public Conversion findLastClosingRate(long serviceId);
	public Conversion findLastClosingRate(Conversion currentRate);
}
