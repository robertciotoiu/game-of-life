package com.robertciotoiu.model;

import java.util.Arrays;
import java.util.Objects;

public class LifeState {
    // Cell map dimensions
    private final int cols;
    private final int rows;

    // This matrix holds current cells
    private final boolean[][] cellsGrid;

    private int generation = 1;

    public LifeState(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cellsGrid = new boolean[rows][cols];
    }

    public LifeState(LifeState pastState) {
        this.cols = pastState.cols;
        this.rows = pastState.rows;
        cellsGrid = deepCopy(pastState);
        this.generation = pastState.generation + 1;
    }

    private static boolean[][] deepCopy(LifeState pastState) {
        boolean[][] copy = new boolean[pastState.rows][pastState.cols];
        for (int r = 0; r < pastState.rows; r++) {
            System.arraycopy(pastState.cellsGrid[r], 0, copy[r], 0, pastState.cols);
        }
        return copy;
    }

    public boolean[][] getCellsGrid() {
        return cellsGrid;
    }

    public void activateCell(int row, int col) {
        cellsGrid[row][col] = true;
    }

    public void deactivateCell(int row, int col) {
        cellsGrid[row][col] = false;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public void setCellState(int i, int j, boolean nextCellState) {
        cellsGrid[i][j] = nextCellState;
    }

    public int getGeneration() {
        return generation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LifeState lifeState = (LifeState) o;
        return cols == lifeState.cols && rows == lifeState.rows && Objects.deepEquals(cellsGrid, lifeState.cellsGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cols, rows, Arrays.deepHashCode(cellsGrid));
    }
}
