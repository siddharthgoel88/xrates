package io.xrates.datamodel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long subscriptionId;

	/* 
	 * A user can have many subscriptions. 
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	/*
	 * Many subscriptions can be made on a single service
	 */
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	
	@Column(name = "active")
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	
}
