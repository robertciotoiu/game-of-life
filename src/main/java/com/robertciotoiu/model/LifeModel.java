package com.robertciotoiu.model;

import com.robertciotoiu.api.LifeListener;

/**
 * Core Game of Life model interface.
 *
 * <p>Extends {@link LifeModelReadOnly} with operations to modify
 * the grid, advance generations, and register listeners for
 * change notifications.</p>
 */
public interface LifeModel extends LifeModelReadOnly {
    void activateCell(int x, int y);
    void deactivateCell(int x, int y);
    void step();

    void addListener(LifeListener l);
    void removeListener(LifeListener l);

    int getGeneration();

    int getAliveCount();
}
