package com.connect4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * Sherif Ebadh
 *
 */
@Entity
@Table(name = "GAME")
public class Game {
	public Game(Player player1,Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Game() {
	}


	public Game(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "game_arr")
	private String gameArray;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "nextPlayer")
	private int nextPlayer;

	@ManyToOne
	@JoinColumn(name = "player1_id")
	private Player player1;
	
	@ManyToOne
	@JoinColumn(name = "player2_id")
	private Player player2;
	
	

	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the nextPlayer
	 */
	public int getNextPlayer() {
		return nextPlayer;
	}

	/**
	 * @param nextPlayer the nextPlayer to set
	 */
	public void setNextPlayer(int nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	/**
	 * @return the gameArray
	 */
	public String getGameArray() {
		return gameArray;
	}

	/**
	 * @param gameArray the gameArray to set
	 */
	public void setGameArray(String gameArray) {
		this.gameArray = gameArray;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	@Override
	public String toString() {
		return "id=" + id;
	}

	

}