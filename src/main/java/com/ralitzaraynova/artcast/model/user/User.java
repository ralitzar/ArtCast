package com.ralitzaraynova.artcast.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance
@DiscriminatorColumn (name="user_type",discriminatorType=DiscriminatorType.STRING,length=20)
@Table (name="user", catalog="artcast")

public abstract class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	protected String fullName;
	protected String email;
	protected String password;
	protected Type type;
	
	private List<Group> groups = new ArrayList<>();
	private List<Address> addresses = new ArrayList<Address>();

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Column(name="full_name", length = 120, nullable = false)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Email
	@NotBlank
	@Column(name ="email", length = 50, nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	@Column(name="password",  length = 50, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_groups", 
	  joinColumns = @JoinColumn(name="user_id"),
	  inverseJoinColumns = @JoinColumn(name = "group_id"))
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	
	public void addNewAddress() {
		if(this.getId() != null){
			Address userAddress = new Address();
				userAddress.setUser(this);
				
				this.getAddresses().add(userAddress);
		}
	}
	
	@Transient
	public boolean isAuthor(){
		boolean isAuthor = false;
		if (this != null && this.getClass().getName() == "author"){
			isAuthor = true;
		}
		return isAuthor;
	}
	
	@Transient
	public boolean isClient(){
		boolean isClient = false;
		if (this != null && this.getClass().getName() == "client"){
			isClient = true;
		}
		return isClient;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
