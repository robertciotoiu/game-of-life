package com.robertciotoiu.model;

public class LifeRules {

    public LifeState step(LifeState state) {
        var nextState = new LifeState(state);
        var cells = state.getCellsGrid();
        for (int i = 0; i < state.getRows(); i++) {
            for (int j = 0; j < state.getCols(); j++) {
                var totalLivingNeighbours = getTotalLivingNeighbours(i, j, state);
                var nextCellState = applyRules(cells[i][j], totalLivingNeighbours);
                nextState.setCellState(i, j, nextCellState);
            }
        }
        return nextState;
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

    /**
     * Counts the number of alive neighbors around a given cell,
     * considering toroidal wrapping at the grid edges.
     *
     */
    private int getTotalLivingNeighbours(int cellRow, int cellCol, LifeState lifeState) {
        int aliveNeighbors = 0;

        // nw
        if (isCellAlive(cellRow - 1, cellCol - 1, lifeState)) aliveNeighbors++;
        // n
        if (isCellAlive(cellRow - 1, cellCol, lifeState)) aliveNeighbors++;
        // ne
        if (isCellAlive(cellRow - 1, cellCol + 1, lifeState)) aliveNeighbors++;

        // w
        if (isCellAlive(cellRow, cellCol - 1, lifeState)) aliveNeighbors++;
        // e
        if (isCellAlive(cellRow, cellCol + 1, lifeState)) aliveNeighbors++;

        // sw
        if (isCellAlive(cellRow + 1, cellCol - 1, lifeState)) aliveNeighbors++;
        // s
        if (isCellAlive(cellRow + 1, cellCol, lifeState)) aliveNeighbors++;
        // se
        if (isCellAlive(cellRow + 1, cellCol + 1, lifeState)) aliveNeighbors++;

        return aliveNeighbors;
    }

    private boolean isCellAlive(int cellRow, int cellCol, LifeState lifeState) {
        var torusCellRow = handleTorusEdge(cellRow, lifeState.getRows());
        var torusCellCol = handleTorusEdge(cellCol, lifeState.getCols());
        var cellsGrid = lifeState.getCellsGrid();

        return cellsGrid[torusCellRow][torusCellCol];
    }

    /**
     * If cell to be checked is out of the screen, we apply torus to keep the edges connected instead of considering
     * out of screen cells as dead.
     *
     * @param cell   to be checked
     * @param length of row or column
     * @return the real position of the cell to be verified
     */
    private static int handleTorusEdge(int cell, int length) {
        if (cell < 0) {
            cell = length - 1;
        } else if (cell > length - 1) {
            cell = 0;
        }
        return cell;
    }
}
