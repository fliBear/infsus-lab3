package hr.fer.infsus.lab3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
	public Role() {
	}
	
	public Role(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}