package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.Categories;
import com.ralitzaraynova.artcast.service.MaterialRegisterService;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;;

@Named
@ViewScoped
public class MaterialRegisterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private MaterialRegisterService materialRegisterService;
	
	@Inject
	private Categories categories;
	
	
	private Material material;
	private Category category;
	
	private List<Category> categoryGroup;
	
	public MaterialRegisterBean() {
		cleaning();
	}
	
	public void initing(){
		if (FacesUtil.isNotPostback()) {
			categoryGroup = categories.groups();			
			}
		}

	private void cleaning() {
		material = new Material();
		category = null;
		categoryGroup = new ArrayList<>();
	}

	public void saving(){
		this.material = materialRegisterService.saveMaterial(this.material);
		FacesUtil.addInfoMessage("Материалът е регистриран успешно!");
	}
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
		
		if(this.material != null){
			this.category = this.material.getCategory();
		}
	}

	public List<Category> getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(List<Category> categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isEdited() {
		return this.material != null && this.material.getId() != null;
	}
	
}