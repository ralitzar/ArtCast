package com.ralitzaraynova.artcast.model.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.model.user.Author;
import com.ralitzaraynova.artcast.model.user.Client;
import com.ralitzaraynova.artcast.model.user.Department;

@Entity
@Table(name="project")
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String number;
	private String name;
	private Date date = new Date();
	private Date dueDate;
	private String serie;
	private Float height;
	private Float length;
	private Float width;
	private Float weight;
	private Complexity complexity = Complexity.НОРМАЛЕН;
	private Integer quantity = 1;
	private BigDecimal price = BigDecimal.ZERO;
	private ProjectState state = ProjectState.ОФЕРТА;
	private Department workshop = Department.ADMINISTRATION;
	private Client client;
	private Author author;
	private SendAddress address;
	private String note;
	private List<ProjectItem> items = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Column(name="project_number",length = 25)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@NotBlank
	@Column(name="project_name", length = 80)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="project_date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="project_dueDate")
	@Temporal(TemporalType.DATE)
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@NotNull
	@Column(name="project_height")
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	
	@NotNull
	@Column(name="project_length")
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	
	@NotNull
	@Column(name="project_width")
	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="project_complexity", length = 15)
	public Complexity getComplexity() {
		return complexity;
	}
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	
	@Column(name="project_weight")
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	@NotBlank
	@Column(name="project_serie", length = 10)
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@Column(name="project_qty", columnDefinition="INTEGER(1)")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="project_price", precision=10, scale=2)
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="project_state", length = 15)
	public ProjectState getState() {
		return state;
	}
	public void setState(ProjectState state) {
		this.state = state;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="project_department", length = 15)
	public Department getWorkshop() {
		return workshop;
	}
	public void setWorkshop(Department workshop) {
		this.workshop = workshop;
	}
	
	@NotNull(message = "полето е задължително")
	@ManyToOne
	@JoinColumn(name="client_id", nullable=false)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@NotNull(message = "полето е задължително")
	@ManyToOne
	@JoinColumn(name="author_id", nullable=false)
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@Embedded
	public SendAddress getAddress() {
		return address;
	}
	public void setAddress(SendAddress address) {
		this.address = address;
	}
	
	@Column(columnDefinition = "text")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<ProjectItem> getItems() {
		return items;
	}
	public void setItems(List<ProjectItem> items) {
		this.items = items;
	}
	
	@Transient
	public boolean isNew(){
		return getId() == null;
	}
	@Transient
	public boolean isExist(){
		return !isNew();
	}
	
	@Transient
	public boolean isОФЕРТА() {
		return ProjectState.ОФЕРТА.equals(this.getState());
	}
	
	@Transient
	private boolean isCanceled() {
		return ProjectState.ОТКАЗАН.equals(this.getState());
	}
	
	// Add new Item Line
	public void addNewItemLine() {
		if(this.isОФЕРТА()){
		Work work = new Work();
		
		ProjectItem item = new ProjectItem();
			item.setWork(work);
			item.setWorkQty(1f);
			item.setProject(this);
		
		this.getItems().add(0, item);
		}
	}
	
	//Remove Item Line
	public void removeItemFirstLine(){
		ProjectItem firstItem = this.getItems().get(0);
		
		if(firstItem != null && firstItem.getWork().getId() == null){
			this.getItems().remove(0);
		}
	}
	
	//E-mail send validation
	@Transient
	public boolean isIncomplete(){
		return this.isNew()|| this.isCanceled();
	}
	
	@Transient
	public BigDecimal getItemsSubTotal(){
		BigDecimal subTotal = BigDecimal.ZERO;
		
		for(ProjectItem item : this.getItems()){
			if(item.getWork() != null && item.getWork().getId() != null){
				subTotal = subTotal.add(item.getItemTotal());
			}
		}
		this.setPrice(subTotal);
		return subTotal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
