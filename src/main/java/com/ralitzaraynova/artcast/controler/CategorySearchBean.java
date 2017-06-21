package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.repository.Categories;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;

public class CategorySearchBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categories categories;
	
	private FilterProperties filter;
	private Category selectedCategory;
	private List<Category> filteredCategories;
	
	public CategorySearchBean() {
		filter = new FilterProperties();
	}
	
	public void removing(){
		categories.removeCategory(selectedCategory);
		filteredCategories.remove(selectedCategory);
		
		FacesUtil.addInfoMessage("Категорията" + selectedCategory.getId()+ "бе изтрита успешно!");
	}
	
	public void searching(){
		filteredCategories = categories.filtered(filter);
	}

	public FilterProperties getFilter() {
		return filter;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<Category> getFilteredCategories() {
		return filteredCategories;
	}

	
	
	
	

}
