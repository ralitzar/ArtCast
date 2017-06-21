package com.ralitzaraynova.artcast.model.user;

public enum Department {
	ADMINISTRATION("Администрация"), 
	MODELING ("Моделиране"), 
	WAXES("Восъци"), 
	CASTING("Леарна"), 
	MECHANIZATION("Машинно"),
	FINISHING("Довършителни"), 
	PATINA("Патина"), 
	PACKAGING("Опаковки"), 
	TRANSPORTING("Транспорт"), 
	WAREHOUSE("Склад");
	
	private String label;

	private Department(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
