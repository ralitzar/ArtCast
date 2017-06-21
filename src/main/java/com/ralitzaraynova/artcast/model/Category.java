package com.ralitzaraynova.artcast.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category", catalog = "artcast" )
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String description;
	private List<Material> materials = new ArrayList<Material>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, length = 60)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.ALL)
	public List<Material> getMaterials() {
		return this.materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
	//add & remove line for Material
	public void addNewMaterialLine() {
		
		Material material = new Material();
		
		material.setCategory(this);
		this.getMaterials().add(0, material);
	}
	
	public void removeMaterialFirstLine(){
		Material firstMaterialLine = this.getMaterials().get(0);
		
		if(firstMaterialLine != null && firstMaterialLine.getId() != null){
			this.getMaterials().remove(0);
		}
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
