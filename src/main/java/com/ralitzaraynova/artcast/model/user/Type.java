package com.ralitzaraynova.artcast.model.user;

public enum Type {

	CLIENT("КЛИЕНТ"),
	AUTHOR("АВТОР"),
	ADMIN("АДМИНИСТРАТОР"),
	WORKER("СЛУЖИТЕЛ");
	
	private String label;
	
	private Type(String label){
		this.label = label;
	}
	public String getLabel() {
        return label;
    }
}
