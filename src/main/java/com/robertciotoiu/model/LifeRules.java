package com.robertciotoiu.model;

public class LifeRules {
    private int getTotalLivingNeighbours(int cellX, int cellY, LifeState lifeState) {
        var cellsGrid = lifeState.getCellsGrid();
        int aliveNeighbors = 0;

        if (isInBounds(cellX - 1, cellY - 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX - 1][cellY - 1]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX - 1, cellY, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX - 1][cellY]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX - 1, cellY + 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX - 1][cellY + 1]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX, cellY - 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX][cellY - 1]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX, cellY + 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX][cellY + 1]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX + 1, cellY - 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX + 1][cellY - 1]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX + 1, cellY, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX + 1][cellY]) {
            aliveNeighbors++;
        }

        if (isInBounds(cellX + 1, cellY + 1, lifeState.getRows(), lifeState.getCols()) && cellsGrid[cellX + 1][cellY + 1]) {
            aliveNeighbors++;
        }

        return aliveNeighbors;
    }

    private boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    private boolean applyRules(boolean cell, int totalLivingNeighbours) {
        if (cell) {
            if (totalLivingNeighbours < 2) return false;
            else if (totalLivingNeighbours == 2 || totalLivingNeighbours == 3) return true;
            else if (totalLivingNeighbours > 3) return false;
        } else {
            if (totalLivingNeighbours == 3) return true;
        }

        // No rule satisfied, the cell state is unchanged
        return cell;
    }

    public LifeState step(LifeState state) {
        var nextState = new LifeState(state);
        var cells = state.getCellsGrid();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                var totalLivingNeighbours = getTotalLivingNeighbours(i, j, state);
                var nextCellState = applyRules(cells[i][j], totalLivingNeighbours);
                nextState.setCellState(i, j, nextCellState);
            }
        }
        return nextState;
    }
}
