package hr.fer.infsus.lab3.models;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Advertisement {

	@Id
	@GeneratedValue
	private Long id;
	private int price;
	private String condition;
	private Date expiryDate;
	private Date advertisementDate;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserAccount user;
	@ManyToOne
	private BoardGame boardGame;
	@ManyToOne
	private City city;
	
	public Advertisement() {
	}

	public Advertisement(Long id, int price, String condition, Date expiryDate, Date advertisementDate,
			UserAccount user, BoardGame boardGame, City city) {
		this.id = id;
		this.price = price;
		this.condition = condition;
		this.expiryDate = expiryDate;
		this.advertisementDate = advertisementDate;
		this.user = user;
		this.boardGame = boardGame;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getAdvertisementDate() {
		return advertisementDate;
	}

	public void setAdvertisementDate(Date advertisementDate) {
		this.advertisementDate = advertisementDate;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boadGame) {
		this.boardGame = boadGame;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
