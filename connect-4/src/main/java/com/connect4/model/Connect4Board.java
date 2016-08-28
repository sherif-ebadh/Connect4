package com.connect4.model;

public class Connect4Board {

	private int[][] matrix;

	public Connect4Board(int[][] matrix) {
		this.matrix = matrix;

	}

	public int[][] getMatrix() {
		return matrix;
	}

	public int getCell(int row, int col) {
		return matrix[row][col];
	}

	public void setCell(int num, int row, int col) {
		matrix[row][col] = num;
	}

	public void print() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.print(this.matrix[row][col] + " ");
				if (col == matrix[row].length - 1) {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}