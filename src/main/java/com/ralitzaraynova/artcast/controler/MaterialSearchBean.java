package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.Materials;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MaterialSearchBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Materials materials;
	
	private FilterProperties filter;
	private Material selectedMaterial;
	private List<Material> filteredMaterials;

	public MaterialSearchBean(){
		filter = new FilterProperties();
	}

	public void removing(){
		materials.removeMaterial(selectedMaterial);
		filteredMaterials.remove(selectedMaterial);
		
		FacesUtil.addInfoMessage("Материалът " + selectedMaterial.getMaterialCode() + "бе изтрит успешно!");
	}

	public void searching(){
		filteredMaterials = materials.filtered(filter);
	}
	
	public Material getSelectedMaterial() {
		return selectedMaterial;
	}
	public void setSelectedMaterial(Material selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}
	
	public List<Material> getFilteredMaterials() {
		return filteredMaterials;
	}
	public FilterProperties getFilter() {
		return filter;
	}
	
}
