package com.ralitzaraynova.artcast.model.user;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("client")
public class Client extends User {

	private static final long serialVersionUID = 1L;
	
	private String cif;
	private String bancAccount;
	private BigDecimal creditLimit;
	private String phone;
	
	public Client() {
		
	}
	
	public Client(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.type = Type.CLIENT;
	}
	
	@Column(name="cif", length = 14)
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	@Column (name="banc_account", length = 25)
	public String getBancAccount() {
		return bancAccount;
	}
	public void setBancAccount(String bancAccount) {
		this.bancAccount = bancAccount;
	}
	
	@Column (name="credit_limit", length = 15)
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	@Column (name="client_phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		return true;
	}
	
}
