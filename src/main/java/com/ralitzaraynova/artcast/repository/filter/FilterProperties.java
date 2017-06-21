package com.ralitzaraynova.artcast.repository.filter;

import java.io.Serializable;

public class FilterProperties implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Work filter properties
	
	private String workCode;
	private String denomination;
	
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode == null ? null : workCode.toUpperCase();
	}
	
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination; 
	}
	
	//Material filter properties
	
	private String materialCode;
	private String materialName;
	private String materialCategoryDescription;
	
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode == null ? null : materialCode.toUpperCase();
	}
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	public String getMaterialCategoryDescription() {
		return materialCategoryDescription;
	}
	public void setMaterialCategoryDescription(String materialCategoryDescription) {
		this.materialCategoryDescription = materialCategoryDescription;
	}
	
	//User filter properties

	private String fullName;
	private String email;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Category filter properties
	
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
