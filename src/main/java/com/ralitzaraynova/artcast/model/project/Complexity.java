package com.ralitzaraynova.artcast.model.project;

public enum Complexity {
	
	ЛЕСЕН("ЛЕСЕН"),
	НОРМАЛЕН ("НОРМАЛЕН"),
	СЛОЖЕН ("СЛОЖЕН"),
	НЕВЪЗМОЖЕН ("НЕВЪЗМОЖЕН");
	
	private String complexityLable;

	private Complexity(String complexityLable) {
		this.complexityLable = complexityLable;
	}
	public String getComplexityLable() {
		return complexityLable;
	}
	
}
