package com.robertciotoiu.model;

import com.robertciotoiu.api.LifeListener;

public interface LifeModel extends LifeModelReadOnly {
    void activateCell(int x, int y);
    void deactivateCell(int x, int y);
    void step();

    void addListener(LifeListener l);
    void removeListener(LifeListener l);

    int getGeneration();

    int getAliveCount();
}
