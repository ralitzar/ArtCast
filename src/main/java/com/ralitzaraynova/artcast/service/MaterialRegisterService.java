package com.ralitzaraynova.artcast.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.Materials;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class MaterialRegisterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Materials materials;
	
	@Transactional
	public Material saveMaterial(Material material){
		Material existingMaterial = materials.byCode(material.getMaterialCode());
		
		if (existingMaterial != null && !existingMaterial.equals(material)){
			throw new BusinessExeption("Материал с посочения код вече съществува!");
		}
		return materials.retain(material);
	}
	
}
