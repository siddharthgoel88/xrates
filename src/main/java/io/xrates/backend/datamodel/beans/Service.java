package io.xrates.backend.datamodel.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="service")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="service_id")
	private long serviceId;
	
	@Column(name = "to_currency", length = 3)
	private String toCurrency;
	
	@Column(name = "from_currency", length = 3)
	private String fromCurrency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private Set<Subscription> subscriptions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private Set<Conversion> conversions;
	
	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}
 