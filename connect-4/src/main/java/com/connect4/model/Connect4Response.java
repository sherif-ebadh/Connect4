package com.connect4.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "isWinner", "isValidEntry", "currentBoard" })
public class Connect4Response extends BaseResponse {

    private boolean isValidEntry;
    protected int[][] currentBoard;
    protected boolean isWinner;

    public Connect4Response(int[][] currentBoard, boolean isWinner, boolean isValidEntry) {
        super();
        this.isValidEntry = isValidEntry;
        this.currentBoard = currentBoard;
        this.isWinner = isWinner;
    }

    @JsonProperty("isValidEntry")
    public boolean isValidCandidate() {
        return isValidEntry;
    }

    @JsonProperty("isValidEntry")
    public void setValidEntry(boolean validEntry) {
        isValidEntry = validEntry;
    }
    
    @JsonProperty("currentBoard")
    public int[][] getBoard() {
        return currentBoard;
    }

    @JsonProperty("currentBoard")
    public void setBoard(int[][] currentBoard) {
        this.currentBoard = currentBoard;
    }

    @JsonProperty("isWinner")
    public boolean isWinner() {
        return isWinner;
    }

    @JsonProperty("isWinner")
    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }


}

