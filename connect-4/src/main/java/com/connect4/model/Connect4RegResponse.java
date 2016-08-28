package com.connect4.model;

import com.connect4.entity.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "playe1Id", "player2Id", "GameId" })
public class Connect4RegResponse extends BaseResponse {

	int player1Id;
	int player2Id;
	int gameId;


    public Connect4RegResponse(Game game) {
        super();
        this.player1Id = game.getPlayer1().getId();
    	this.player2Id = game.getPlayer2().getId();
    	this.gameId = game.getId();
    }


    @JsonProperty("player1Id")
	public int getPlayer1Id() {
		return player1Id;
	}


    @JsonProperty("player1Id")
	public void setPlayer1Id(int player1Id) {
		this.player1Id = player1Id;
	}

    @JsonProperty("player2Id")
    public int getPlayer2Id() {
		return player2Id;
	}
    
    @JsonProperty("player2Id")
    public void setPlayer2Id(int player2Id) {
		this.player2Id = player2Id;
	}


    @JsonProperty("gameId")
    public int getGameId() {
		return gameId;
	}


    @JsonProperty("gameId")
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}



}

