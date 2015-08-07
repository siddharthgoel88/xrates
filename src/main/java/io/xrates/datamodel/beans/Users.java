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
	@Column(name = "id")
	private long user_id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "email", nullable=false)
	private String email;
	
	@Column(name = "contact_number")
	private long contact_number;
	
	@Column(name = "isd_code", length = 3)
	private int isd_code;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact_number() {
		return contact_number;
	}

	public void setContact_number(long contact_number) {
		this.contact_number = contact_number;
	}

	public int getIsd_code() {
		return isd_code;
	}

	public void setIsd_code(int isd_code) {
		this.isd_code = isd_code;
	}
	
	
	
}
