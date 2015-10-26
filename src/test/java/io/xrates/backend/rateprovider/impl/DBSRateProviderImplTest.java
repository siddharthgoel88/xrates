package io.xrates.backend.rateprovider.impl;

import io.xrates.backend.constants.Constants;
import io.xrates.backend.datamodel.util.XratesDBUtil;
import io.xrates.backend.exceptions.RateProviderException;

import java.util.Currency;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class DBSRateProviderImplTest {
	
	@InjectMocks
	private DBSRateProviderImpl dbsRateProviderImpl = null;
	
	@Mock
	private XratesDBUtil xratesDBUtil;
	
	private Currency sgd = Currency.getInstance("SGD");
	private Logger log = LoggerFactory.getLogger(DBSRateProviderImplTest.class.getName());
	
	public DBSRateProviderImplTest() throws RateProviderException {
		dbsRateProviderImpl = new DBSRateProviderImpl();
		try {
			dbsRateProviderImpl.update();
		} catch (RateProviderException e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void testUSD() {
		Currency usd = Currency.getInstance("USD");
		double rate = dbsRateProviderImpl.convert(sgd, usd);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testEUR() {
		Currency eur = Currency.getInstance("EUR");
		double rate = dbsRateProviderImpl.convert(sgd, eur);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testGBP() {
		Currency gbp = Currency.getInstance("GBP");
		double rate = dbsRateProviderImpl.convert(sgd, gbp);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testAUD() {
		Currency aud = Currency.getInstance("AUD");
		double rate = dbsRateProviderImpl.convert(sgd, aud);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testCAD() {
		Currency cad = Currency.getInstance("CAD");
		double rate = dbsRateProviderImpl.convert(sgd, cad);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testNZD() {
		Currency nzd = Currency.getInstance("NZD");
		double rate = dbsRateProviderImpl.convert(sgd, nzd);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testDKK() {
		Currency dkk = Currency.getInstance("DKK");
		double rate = dbsRateProviderImpl.convert(sgd, dkk);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testHDK() {
		Currency hkd = Currency.getInstance("HKD");
		double rate = dbsRateProviderImpl.convert(sgd, hkd);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testNOK() {
		Currency nok = Currency.getInstance("NOK");
		double rate = dbsRateProviderImpl.convert(sgd, nok);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testSEK() {
		Currency sek = Currency.getInstance("SEK");
		double rate = dbsRateProviderImpl.convert(sgd, sek);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testCHF() {
		Currency chf = Currency.getInstance("CHF");
		double rate = dbsRateProviderImpl.convert(sgd, chf);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testCNY() {
		Currency cny = Currency.getInstance("CNY");
		double rate = dbsRateProviderImpl.convert(sgd, cny);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testJPY() {
		Currency jpy = Currency.getInstance("JPY");
		double rate = dbsRateProviderImpl.convert(sgd, jpy);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testTHB() {
		Currency thb = Currency.getInstance("THB");
		double rate = dbsRateProviderImpl.convert(sgd, thb);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testIDR() {
		Currency idr = Currency.getInstance("IDR");
		double rate = dbsRateProviderImpl.convert(sgd, idr);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testBND() {
		Currency bnd = Currency.getInstance("BND");
		double rate = dbsRateProviderImpl.convert(sgd, bnd);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testZAR() {
		Currency zar = Currency.getInstance("ZAR");
		double rate = dbsRateProviderImpl.convert(sgd, zar);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testINR() {
		Currency inr = Currency.getInstance("INR");
		double rate = dbsRateProviderImpl.convert(sgd, inr);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testKRW() {
		Currency krw = Currency.getInstance("KRW");
		double rate = dbsRateProviderImpl.convert(sgd, krw);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testLKR() {
		Currency lkr = Currency.getInstance("LKR");
		double rate = dbsRateProviderImpl.convert(sgd, lkr);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testPHP() {
		Currency php = Currency.getInstance("PHP");
		double rate = dbsRateProviderImpl.convert(sgd, php);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testSAR() {
		Currency sar = Currency.getInstance("SAR");
		double rate = dbsRateProviderImpl.convert(sgd, sar);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
	
	@Test
	public void testTWD() {
		Currency twd = Currency.getInstance("TWD");
		double rate = dbsRateProviderImpl.convert(sgd, twd);
		Assert.assertNotEquals(Constants.RATE_NOT_FOUND, rate);
	}
}
