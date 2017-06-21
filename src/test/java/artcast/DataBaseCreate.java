package artcast;

import javax.persistence.Persistence;

public class DataBaseCreate {
	public static void main (String[] args){
		Persistence.createEntityManagerFactory("artcastPU");
	}
}
