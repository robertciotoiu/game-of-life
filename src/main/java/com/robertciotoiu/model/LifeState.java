package com.robertciotoiu.model;

import java.util.Arrays;
import java.util.Objects;

public class LifeState {
    // Cell map dimensions
    private final int cols;
    private final int rows;

    // This matrix holds current cells
    private final boolean[][] cellsGrid;

//    // In this matrix we build the next step cells
//    private final boolean[][] nextCellsGrid;

    public LifeState(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cellsGrid = new boolean[rows][cols];
//        nextCellsGrid = cellsGrid;
    }

    public LifeState(LifeState pastState) {
        cols = pastState.cols;
        rows = pastState.rows;
        cellsGrid = pastState.cellsGrid;
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
