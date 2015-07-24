package io.xrates;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.xrates.MainScheduler;
import io.xrates.rateprovider.DBSRateProviderImpl;
import io.xrates.rateprovider.IRateProvider;

@Configuration
@ComponentScan(value={"io.xrates"})
public class DIConfiguration {
	
	@Bean
	public List<IRateProvider> getRateProviderType(){
		List<IRateProvider> objList = new ArrayList<>();
		objList.add(dbsRateProvider());
		return objList;
	}
	
	@Bean
	public DBSRateProviderImpl dbsRateProvider(){
		return new DBSRateProviderImpl();
	}
}
