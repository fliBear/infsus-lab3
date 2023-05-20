package hr.fer.infsus.lab3.models;

import jakarta.persistence.*;

@Entity
public class BoardGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int noPlayersMin;
	private int noPlayersMax;
	private int age;
	private int avgPlayingTime;
	@ManyToOne
	private Publisher publisher;
	@ManyToOne
	private Language language;
	@ManyToOne
	private Genre genre;
	
	public BoardGame() {
	}

	public BoardGame(Long id, String name, int noPlayersMin, int noPlayersMax, int age, int avgPlayingTime,
			Publisher publisher, Language language, Genre genre) {
		super();
		this.id = id;
		this.name = name;
		this.noPlayersMin = noPlayersMin;
		this.noPlayersMax = noPlayersMax;
		this.age = age;
		this.avgPlayingTime = avgPlayingTime;
		this.publisher = publisher;
		this.language = language;
		this.genre = genre;
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

	public int getNoPlayersMin() {
		return noPlayersMin;
	}

	public void setNoPlayersMin(int noPlayersMin) {
		this.noPlayersMin = noPlayersMin;
	}

	public int getNoPlayersMax() {
		return noPlayersMax;
	}

	public void setNoPlayersMax(int noPlayersMax) {
		this.noPlayersMax = noPlayersMax;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAvgPlayingTime() {
		return avgPlayingTime;
	}

	public void setAvgPlayingTime(int avgPlayingTime) {
		this.avgPlayingTime = avgPlayingTime;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
