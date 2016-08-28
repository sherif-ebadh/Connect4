package com.connect4.utils;

import com.connect4.entity.Game;
import com.connect4.model.Connect4Board;

public class Connect4Solver {

	private Connect4Board board;

	public Connect4Board getBoard() {
		return board;
	}

	public void setBoard(Connect4Board board) {
		this.board = board;
	}

	public Connect4Solver(Connect4Board board) {
		this.board = board;
	}

	/**
	 * Check if a number for the given cell is part of a possible Sudoku
	 * solution.
	 *
	 * @param num.
	 * @param row.
	 * @param col.
	 * @return true if num is part of a possible Sudoku solution or false
	 *         otherwise.
	 */
	public boolean checkEntry(int playerId,int row,  int col) {
		if(row != -1) {
			board.setCell(playerId, row, col);
			return true;
		}
		return false;
	}

	/**
	 * THis Method Return the First Row starting from Bottom where the player symplo will placed
	 * @param board
	 * @param col
	 * @return
	 */
	public int getNearstRow(Connect4Board board, int col) {
		for (int i = board.getMatrix().length - 1; i >= 0; i--) {
			if (board.getCell(i, col) == 0) {
				return i;
			}
		}
		return -1;
	}
	// getTheRow()

	public boolean checkWin(int player, int[][] gridTable, int rowNum, int colNum) {
		// For checking whether any win or lose condition is reached. Returns 1
		// if win or lose is reached. else returns 0
		// gridTable[][] is the game matrix(can be any number of rows and
		// columns between 4 and 40)
		// colNum is the column number where the last token was placed
		// rowNum is the row number where the last token was placed
		// maxRow is the number of rows in my grid
		// maxCol is the number of columns in my grid

		// send the player ID as input
		// int player = gridTable[rowNum][colNum]; //player ID
		int count = 0;
		int maxCol = gridTable[0].length; // 4
		int maxRow = gridTable.length; // 3

		// Horizontal check
		for (int i = 0; i < maxCol; i++) {
			if (gridTable[rowNum][i] == player)
				count++;
			else
				count = 0;

			if (count >= 4)
				return true;
		}
		count = 0 ;
		// Vertical check
		for (int i = 0; i < maxRow; i++) {
			if (gridTable[i][colNum] == player)
				count++;
			else
				count = 0;

			if (count >= 4)
				return true;
		}

		count = 0;

		// top-left to bottom-right
		if (checkDiagnolLeftToRight(player, gridTable)) {
			return true;
		}

		count = 0;
		
		if (checkDiagnolRightToLeft(player, gridTable)) {
			return true;
		}

		return false;
	}

	/**
	 * @param player
	 * @param gridTable
	 */
	private boolean checkDiagnolLeftToRight(int player, int[][] gridTable) {
		int count;
		int colMax = gridTable[0].length; // 4
		int rowMax = gridTable.length; // 3
		int rowStart;
		int colStart;

		// top-left to bottom-right - green diagonals
		for (rowStart = 0; rowStart < rowMax - 3; rowStart++) {
			count = 0;
			int row, col;
			for (row = rowStart, col = 0; row < rowMax && col < colMax; row++, col++) {
				if (gridTable[row][col] == player) {
					count++;
					if (count >= 4)
						return true;
				} else {
					count = 0;
				}
			}
		}

		// top-left to bottom-right - red diagonals
		for (colStart = 1; colStart < colMax - 3; colStart++) {
			count = 0;
			int row, col;
			for (row = 0, col = colStart; row < rowMax && col < colMax; row++, col++) {
				if (gridTable[row][col] == player) {
					count++;
					if (count >= 4)
						return true;
				} else {
					count = 0;
				}
			}
		}

		return false;
	}

	/**
	 * @param player
	 * @param gridTable
	 */
	private boolean checkDiagnolRightToLeft(int player, int[][] gridTable) {
		int count;
		int colMax = gridTable[0].length; // 4
		int rowMax = gridTable.length; // 3
		int rowStart;
		int colStart;

		// top-left to bottom-right - green diagonals
		for (rowStart = 0; rowStart < rowMax - 3; rowStart++) {
			count = 0;
			int row, col;
			for (row = rowStart, col = colMax - 1; row < rowMax && col >= 0; row++, col--) {
				if (gridTable[row][col] == player) {
					count++;
					if (count >= 4)
						return true;
				} else {
					count = 0;
				}
			}
		}

		// top-left to bottom-right - red diagonals
		for (colStart = colMax - 2; colStart > 2; colStart--) {
			count = 0;
			int row, col;
			for (row = 0, col = colStart; row < rowMax && col >= 0; row++, col--) {
				if (gridTable[row][col] == player) {
					count++;
					if (count >= 4)
						return true;
				} else {
					count = 0;
				}
			}
		}

		return false;
	}

	public boolean checkPlayer(Game game, Integer playerId) {
		if(game.getPlayer1().getId() == playerId.intValue()){
			if(game.getNextPlayer() == playerId.intValue()){
			return true;
			}
		}else if(game.getPlayer2().getId() == playerId.intValue()){
			if(game.getNextPlayer() == playerId.intValue()){
				return true;
			}
		}
		return false;
		}
	}

