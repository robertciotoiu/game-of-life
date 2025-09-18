package com.robertciotoiu.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the state of the Game of Life grid at a single generation.
 *
 * <p>Encapsulates a 2D boolean array for cell states together with
 * the current generation counter. Equality and hash code are defined
 * by comparing grid contents and dimensions.</p>
 *
 * <p>This class is intended for use within the simulation loop.
 * Each call to {@code step()} produces a new {@code LifeState} that
 * replaces the previous one in the model.</p>
 */
public class LifeState {
    private final int rows;
    private final int cols;
    private final boolean[][] cellsGrid;

    private int generation = 1;

    public LifeState(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cellsGrid = new boolean[rows][cols];
    }

    public LifeState(LifeState pastState) {
        this.rows = pastState.rows;
        this.cols = pastState.cols;
        this.generation = pastState.generation;
        cellsGrid = deepCopy(pastState);
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

    public void increaseGeneration() {
        this.generation++;
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
