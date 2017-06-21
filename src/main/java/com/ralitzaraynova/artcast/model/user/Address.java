package com.ralitzaraynova.artcast.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {

		private Long id;
		private User user;
		private String street;
		private Integer number;
		private String city;
		private String postalCode;
		private String country;
		private String personalPhone;
		private String mobilePhone;
			
		@Id
		@GeneratedValue (strategy=GenerationType.IDENTITY)
		@Column(name="address_id")
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
	
		@ManyToOne
		@JoinColumn(name="user_id", nullable=false)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		@Column(name="street", length = 80)
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		
		@Column(name="street_number", length = 10)
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		
		@Column(name="city", length=50)
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		@Column(name="postal_code", length=10)
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		
		@Column(name="country", length=80)
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
		@Column(name="personal_phone", length=25)
		public String getPersonalPhone() {
			return personalPhone;
		}
		public void setPersonalPhone(String personalPhone) {
			this.personalPhone = personalPhone;
		}
		
		@Column(name="mobile_phone", length=25)
		public String getMobilePhone() {
			return mobilePhone;
		}
		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Address other = (Address) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
		
		
}
