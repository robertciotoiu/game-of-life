package com.robertciotoiu.model;

import com.robertciotoiu.api.LifeChangeEvent;
import com.robertciotoiu.api.LifeListener;

import java.util.ArrayList;
import java.util.List;

public class LifeModelImpl implements LifeModel {
    private LifeState state;
    private final LifeRules rules;

    // Reactive listeners
    // We have multiple listeners for supporting actions from multiple GUI places(mouse input, gui buttons, etc.)
    private final List<LifeListener> listeners = new ArrayList<>();

    public LifeModelImpl(int rows, int cols) {
        state = new LifeState(rows, cols);
        rules = new LifeRules();
    }

    @Override
    public void addListener(LifeListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListener(LifeListener l) {
        listeners.remove(l);
    }

    @Override
    public int getGeneration() {
        return state.getGeneration();
    }

    @Override
    public int getAliveCount() {
        int totalAlive = 0;
        for (int i = 0; i < state.getRows(); i++) {
            for (int j = 0; j < state.getCols(); j++) {
                if (state.getCellsGrid()[i][j]) totalAlive++;
            }
        }
        return totalAlive;
    }

    private void fire(LifeChangeEvent e) {
        for (LifeListener l : listeners) {
            l.gridChanged(e);
        }
    }

    @Override
    public void step() {
        // apply life rules to proceed to the next step
        state = rules.step(state);
        // notify everyone
        fire(LifeChangeEvent.all());
    }

    @Override
    public void activateCell(int x, int y) {
        state.activateCell(x, y);
    }

    @Override
    public void deactivateCell(int x, int y) {
        state.deactivateCell(x, y);
    }

    @Override
    public int getRows() {
        return state.getRows();
    }

    @Override
    public int getCols() {
        return state.getCols();
    }

    @Override
    public boolean[][] getCellsGrid() {
        return state.getCellsGrid();
    }
}
