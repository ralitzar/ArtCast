package com.ralitzaraynova.artcast.model.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("worker")
public class Worker extends User {

	private static final long serialVersionUID = 1L;
	
	private String workerId;
	private String position;
	private LocalDateTime addmissionDate;
	private LocalDateTime endDate;
	private BigDecimal salary;
	private Boolean state;
	private Department department;
	
	public Worker() {
		
	}
	
	public Worker(String fullName, String email, String password){
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.type = Type.WORKER;
	}
	
	@Column(name="egn", length =15)
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	
	@Column (length =20)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public LocalDateTime getAddmissionDate() {
		return addmissionDate;
	}
	public void setAddmissionDate(LocalDateTime addmissionDate) {
		this.addmissionDate = addmissionDate;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	@Column (name="state")
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="department",length =15)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((workerId == null) ? 0 : workerId.hashCode());
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
		Worker other = (Worker) obj;
		if (workerId == null) {
			if (other.workerId != null)
				return false;
		} else if (!workerId.equals(other.workerId))
			return false;
		return true;
	}
	
}
