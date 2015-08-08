package io.xrates.datamodel.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Provider")
public class Provider {
	@Id
	@Column(name = "provider_id")
	private long providerId;
	
	@Column(name = "provider_name")
	private String providerName;
	
	@OneToMany
	private List<Conversion> conversions;

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public List<Conversion> getConversions() {
		return conversions;
	}

	public void setConversions(List<Conversion> conversions) {
		this.conversions = conversions;
	}
	
	
}
