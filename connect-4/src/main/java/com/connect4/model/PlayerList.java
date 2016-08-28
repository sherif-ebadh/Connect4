package com.connect4.model;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author
 *
 */
public class PlayerList {

	private String player1Name;
	private String player2Name;
	/**
	 * @return the player1Name
	 */
	public String getPlayer1Name() {
		return player1Name;
	}
	/**
	 * @param player1Name the player1Name to set
	 */
	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}
	/**
	 * @return the player2Name
	 */
	public String getPlayer2Name() {
		return player2Name;
	}
	/**
	 * @param player2Name the player2Name to set
	 */
	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}
	

	

}