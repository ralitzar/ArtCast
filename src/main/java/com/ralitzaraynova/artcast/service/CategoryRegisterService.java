package com.ralitzaraynova.artcast.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.repository.Categories;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class CategoryRegisterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categories categories;
	
	@Transactional
	public Category saveCategory (Category category){
		Category existingCategory = categories.byDescription(category.getDescription());
		
		if(existingCategory != null && !existingCategory.equals(category)){
			throw new BusinessExeption("Категория с това име вече съществува!");
		}
		return categories.retain(category);
	}

}
