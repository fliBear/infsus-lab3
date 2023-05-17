package hr.fer.infsus.lab3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class City {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	private County county;
	
	public City() {
	}

	public City(Long id, String name, County county) {
		this.id = id;
		this.name = name;
		this.county = county;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}
	
}
