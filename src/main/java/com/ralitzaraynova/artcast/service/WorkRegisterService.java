package com.ralitzaraynova.artcast.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.repository.Works;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class WorkRegisterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Works works;
	
	@Transactional
	public Work saveWork(Work work){
		Work existingWork = works.byCode(work.getWorkCode());
		
		if (existingWork != null && !existingWork.equals(work)){
			throw new BusinessExeption("Дейност с посочения код вече съществува!");
		}
		return works.retain(work);
	}
	
	
	
}
