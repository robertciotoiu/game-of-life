package com.robertciotoiu.model;

/**
 * Read-only view of the Game of Life model.
 *
 * <p>Exposes grid dimensions, generation count, and the current
 * cell states but does not allow mutation. Intended for use by
 * views such as {@code LifePanel} that need to render the grid
 * without modifying it.</p>
 */
public interface LifeModelReadOnly {
    int getRows();
    int getCols();
    boolean[][] getCellsGrid();
}
