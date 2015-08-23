package io.xrates.backend.datamodel.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "conversion")
@Check(constraints = "conversion_rate >= 0")
public class Conversion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "conversion_id")
	private long conversionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "conversion_timestamp")
	private Date conversionTimestamp;
	
	@Column(name = "conversion_rate", precision = 6)
	private float conversionRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public long getConversionId() {
		return conversionId;
	}

	public void setConversionId(long conversionId) {
		this.conversionId = conversionId;
	}

	public Date getConversionTimestamp() {
		return conversionTimestamp;
	}

	public void setConversionTimestamp(Date conversionTimestamp) {
		this.conversionTimestamp = conversionTimestamp;
	}

	public float getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(float conversionRate) {
		this.conversionRate = conversionRate;
	}
	
}