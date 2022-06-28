package com.codingdojo.belt.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="babyNames")
public class BabyName {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@NotEmpty(message="Name is required!")
	@Size(min=2, message="Name must be at least 3 characters")
	@Column(unique=true)
	private String newName;
	
	@NotBlank(message="Gender is required!")
	private String gender;
	
	@NotEmpty(message="Origin is required!")
	@Size(min=2, message="Origin must be at least 2 characters")
	private String origin;
	
	@NotEmpty(message="Meaning is required!")
	@Size(min=2, message="Meaning must be at least 2 characters")
	private String meaning;
	
	private Long votes;
	
	private String owner;
	//============ RED BELT ==============
//		@ManyToOne(fetch=FetchType.LAZY)
//	    @JoinColumn(name="user_id")
//	    private User user;
		
	// =========== BLACK BELT =========
		@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "users_babynames", 
	        joinColumns = @JoinColumn(name = "babyName_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private List<User> users;
		
		
	public BabyName() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNewName() {
		return newName;
	}


	public void setNewName(String newName) {
		this.newName = newName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getMeaning() {
		return meaning;
	}


	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public Long getVotes() {
		return votes;
	}


	public void setVotes(Long votes) {
		this.votes = votes;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	
	
	
}
