package io.xrates.datamodel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "email", nullable=false)
	private String email;
	
	@Column(name = "contact_number")
	private long contact_number;
	
	@Column(name = "isd_code", length = 3)
	private int isd_code;
	
}
