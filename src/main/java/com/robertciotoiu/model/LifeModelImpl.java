package com.robertciotoiu.model;

import com.robertciotoiu.api.LifeChangeEvent;
import com.robertciotoiu.api.LifeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Default in-memory implementation of the {@link LifeModel}.
 *
 * <p>Maintains the current {@link LifeState}, applies {@link LifeRules}
 * to advance generations, and supports listener notification via the
 * observer pattern.</p>
 *
 * <h3>Event model</h3>
 * <ul>
 *   <li>{@link #step()} advances the simulation by one generation
 *       and notifies all registered {@link LifeListener}s with a
 *       {@link com.robertciotoiu.api.LifeChangeEvent}.</li>
 *   <li>{@link #activateCell(int, int)} and
 *       {@link #deactivateCell(int, int)} mutate the current grid
 *       state silently without firing events. This allows bulk
 *       initialization or setup without flooding the GUI with repaints.</li>
 * </ul>
 *
 * <p>As a result, GUI updates occur strictly once per generation step,
 * keeping rendering consistent and efficient.</p>
 *
 * <p>This implementation is not thread-safe and is intended for use on
 * the Swing Event Dispatch Thread.</p>
 */
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

    /**
     * Advances the model by one generation.
     *
     * <p>Applies Conway's rules via {@link LifeRules}, replaces the current
     * {@link LifeState}, and notifies all registered listeners with a
     * {@link com.robertciotoiu.api.LifeChangeEvent}.</p>
     */
    @Override
    public void step() {
        // Apply life rules to proceed to the next step
        state = rules.step(state);
        // Notify everyone
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
