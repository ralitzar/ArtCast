package com.ralitzaraynova.artcast.model.project;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ralitzaraynova.artcast.model.Work;

@Entity
@Table(name="project_items")
public class ProjectItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Work work;
	private String unit;
	private Float workQty;
	private BigDecimal unitPrice;
	private Project project;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="item_unit",length=10)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name="unitPrice", precision=10, scale=2)
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column (name="quantity", precision=10, scale=2)
	public Float getWorkQty() {
		return workQty;
	}
	public void setWorkQty(Float workQty) {
		this.workQty = workQty;
	}
	
	@ManyToOne
	@JoinColumn(name="work_id", nullable=false)
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	@ManyToOne
	@JoinColumn(name="project_id",nullable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	// Associate Work to Item
	@Transient
	public boolean isAssociatedWork(){
		return this.getWork() != null && this.getWork().getId() != null;
	}
	
	//Calculate ItemTotal
	@Transient
	public BigDecimal getItemTotal(){
		if(isAssociatedWork()){
			return this.getWork().getUnitPrice().multiply(new BigDecimal(this.getWorkQty()));
		}
		return BigDecimal.ZERO;
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
		ProjectItem other = (ProjectItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
