package com.ralitzaraynova.artcast.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table (name = "work")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Work implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	private String workCode;
	private String denomination;
	private WorkType workType;
	private Category workMaterial;
	private String unit;
	private BigDecimal unitPrice;
	private String note;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name = "work_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Column (name="work_code", length = 10, nullable = false)
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	
	@NotBlank
	@Column(name="denomination", length = 120, nullable = false)
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="work_type")
	public WorkType getWorkType() {
		return workType;
	}
	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn (name="category_id" )
	public Category getWorkMaterial() {
		return workMaterial;
	}
	public void setWorkMaterial(Category workMaterial) {
		this.workMaterial = workMaterial;
	}
	
	@NotBlank
	@Column(name="unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name="work_price", precision = 10, scale = 2)
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column(name="note", columnDefinition = "text")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
		Work other = (Work) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
