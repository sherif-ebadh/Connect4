package com.connect4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * Sherif Ebadh
 *
 */
@Entity
@Table(name = "player")
public class Player {
	public Player(String firstName) {
		this.firstName = firstName;
	}
	public Player() {
	}

	public Player(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "Please Enter your Name")
	@Column(name = "first_name")
	private String firstName;

//	@OneToMany(mappedBy = "game")
//	private Set<Game> game;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


//	/**
//	 * @return the game
//	 */
//	public Set<Game> getGame() {
//		return game;
//	}
//
//	/**
//	 * @param game the game to set
//	 */
//	public void setGame(Set<Game> game) {
//		this.game = game;
//	}

	@Override
	public String toString() {
		return "id=" + id + ", first name=" + firstName;
	}

}