package com.ralitzaraynova.artcast.controler;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.util.EditedProject;

@Named
@RequestScoped
public class UploadImageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UploadedFile file;
	private List<String> images = new ArrayList<String>();;
	
	@Inject
	@EditedProject
	private Project project;

	public void upload() {

		String filePath = "C:/pics/" + (project != null ? project.getId() : "");

		if (file != null) {
			try {
				InputStream fin = file.getInputstream();
				File targetFile = new File(filePath + "/" + file.getFileName());
				if (targetFile.getParentFile() != null) {
					targetFile.getParentFile().mkdirs();
				}
				Files.copy(fin, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				FacesMessage msg = new FacesMessage("Файлът", file.getFileName() + " е качен успешно.");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {
				System.out.println("Exception-File Upload." + e.getMessage());
			}
		} else {
			FacesMessage msg = new FacesMessage("Моля изберете файл!!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	@PostConstruct
	public void readImages() {
		
		String filePath = "C:/pics/" + (project != null ? project.getId() : "");
		File folder = new File(filePath);
	
			try {	
				for (File f : folder.listFiles()) {
					if (f.isFile()) {
						images.add( "/pics/" + project.getId() + "/" + f.getName());
						}
					}
			} catch (Exception e){
				System.out.println("Exception-File Read." + e.getMessage());
				// FacesUtil.addErrorMessage("Няма качени снимки!");
			} 
			
	}

	
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
