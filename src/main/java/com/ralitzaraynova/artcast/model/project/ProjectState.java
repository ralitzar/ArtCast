package com.ralitzaraynova.artcast.model.project;

public enum ProjectState {

	ОФЕРТА("ОФЕРТА"), 
	ПРИЕТ("ПРИЕТ"), 
	МОДЕЛИРАНЕ("МОДЕЛИРАНЕ"),	
	КЪЛЪПИРАНЕ ("КЪЛЪПИРАНЕ"), 
	ЛЕЕНЕ("ЛЕЕНЕ"), 
	ДОВЪРШИТЕЛНИ("ДОВЪРШИТЕЛНИ"), 
	ПАТИНИРАНЕ ("ПАТИНИРАНЕ"), 
	ОПАКОВАНЕ("ОПАКОВАНЕ"),
	ТРАНСПОРТ ("ТРАНСПОРТ"),
	МОНТИРАНЕ ("МОНТИРАНЕ"), 
	ФИНАЛИЗИРАН("ФИНАЛИЗИРАН"),
	ОТКАЗАН("ОТКАЗАН");
	
	private String label;

	private ProjectState(String label){
		this.label = label;
	}
	public String getLabel() {
		return label;
	}

}
