package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.Materials;
import com.ralitzaraynova.artcast.service.CategoryRegisterService;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoryRegisterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Materials materials;
	
	@Inject
	private CategoryRegisterService categoryRegisterService;
	
	private Category category;
	private Material categoryMaterial;
	
	private List<Material> materialsGroup;

	public CategoryRegisterBean() {
		cleaning();
	}
	
	public void initing(){
		if (FacesUtil.isNotPostback()) {
			setMaterialsGroup(materials.byCategory(category));
		}
	}
	
	private void cleaning() {
		category = new Category();
		setCategoryMaterial(null);
		materialsGroup = new ArrayList<>();
	}
	
	public void saving(){
		this.category = categoryRegisterService.saveCategory(category);
		
		FacesUtil.addInfoMessage("Категорията е регистрирана успешно!");
	}
	
	public void searching(){
		materialsGroup = materials.byCategory(category);
	}

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Material> getMaterialsGroup() {
		return materialsGroup;
	}
	public void setMaterialsGroup(List<Material> materialsGroup) {
		this.materialsGroup = materialsGroup;
	}

	public Material getCategoryMaterial() {
		return categoryMaterial;
	}
	public void setCategoryMaterial(Material categoryMaterial) {
		this.categoryMaterial = categoryMaterial;
	}

}
