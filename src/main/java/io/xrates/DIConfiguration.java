package io.xrates;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.xrates.rateprovider.IRateProvider;
import io.xrates.rateprovider.impl.DBSRateProviderImpl;
import io.xrates.rateprovider.impl.OCBCRateProviderImpl;

@Configuration
@ComponentScan(value={"io.xrates"})
public class DIConfiguration {
	
	@Bean
	public List<IRateProvider> getRateProviderType(){
		List<IRateProvider> objList = new ArrayList<>();
		objList.add(dbsRateProvider());
		objList.add(ocbcRateProvider());
		return objList;
	}
	
	@Bean
	public DBSRateProviderImpl dbsRateProvider(){
		return new DBSRateProviderImpl();
	}
	
	@Bean
	public OCBCRateProviderImpl ocbcRateProvider(){
		return new OCBCRateProviderImpl();
	}
}
