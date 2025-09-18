package com.robertciotoiu.controller;

import com.robertciotoiu.model.LifeModel;
import com.robertciotoiu.view.LifePanel;

import javax.swing.*;

/**
 * Controller responsible for running the Game of Life simulation loop.
 *
 * <p>Uses a Swing {@link javax.swing.Timer} to periodically advance the model
 * and update the view. The Swing timer guarantees that its callbacks run
 * on the Event Dispatch Thread (EDT), so UI updates are safe.</p>
 */
public class LifeController {
    private static final int REFRESH_RATE = 1200; // in millis
    private final LifeModel model;
    private final LifePanel view;
    private final JLabel statusLabel;
    private final Timer timer;

    public LifeController(LifeModel model, LifePanel view, JLabel statusLabel) {
        this.model = model;
        this.view = view;
        this.statusLabel = statusLabel;
        this.model.addListener(view);
        timer = new Timer(REFRESH_RATE, e -> {
            model.step();
            updateStatus();
        });
        updateStatus();
    }

    /**
     * Starts the main game loop
     */
    public void startGame(){
        timer.start();
    }

    private void updateStatus() {
        int alive = model.getAliveCount();
        int generation = model.getGeneration();
        statusLabel.setText("Generation: " + generation + " | Alive: " + alive + " out of: " + model.getRows() * model.getCols());
    }
}
