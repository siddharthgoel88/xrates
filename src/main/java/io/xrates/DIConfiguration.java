package io.xrates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.xrates.MainScheduler;
import rateprovider.DBSRateProviderImpl;

@Configuration
@ComponentScan(value={"io.xrates"})
public class DIConfiguration {
	
	@Bean
	public DBSRateProviderImpl getRateProviderType(){
		return new DBSRateProviderImpl();
	}
}
