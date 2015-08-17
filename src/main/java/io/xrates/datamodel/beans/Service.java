package io.xrates.datamodel.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "provider_id"))
	private Provider provider;

	@OneToMany
	private List<Subscription> subscriptions;
	
	@OneToMany
	private List<Conversion> conversions;
	
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

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}
 