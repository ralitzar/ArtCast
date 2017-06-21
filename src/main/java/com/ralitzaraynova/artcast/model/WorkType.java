package com.ralitzaraynova.artcast.model;

public enum WorkType {
	
	ДЕЙНОСТ("ДЕЙНОСТ"), 
	ДОВЪРШИТЕЛНИ("ДОВЪРШИТЕЛНИ"), 
	ДРУГИ("ДРУГИ");
	
	private String label;
	
	private WorkType(String label){
		this.label = label;
	}
	
	public String getLabel() {
        return label;
    }
	
}
