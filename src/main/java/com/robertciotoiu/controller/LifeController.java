package com.robertciotoiu.controller;

import com.robertciotoiu.model.LifeModel;
import com.robertciotoiu.view.LifePanel;

import javax.swing.*;
import java.util.Random;

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
        init();
        timer = new Timer(REFRESH_RATE, _ -> {
            model.step();
            updateStatus();
        });
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

    private void init() {
        rndActivateCells();
        updateStatus();
    }

    private void rndActivateCells() {
        Random rnd = new Random();
        var aliveInitCounter = 0;

        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                if (shouldBeAliveWithChance(rnd, 15)) {
                    model.activateCell(i, j);
                    aliveInitCounter++;
                }
            }
        }
        System.out.println("Init active cells: " + aliveInitCounter + " / " + model.getRows() * model.getCols());
    }

    private static boolean shouldBeAliveWithChance(Random rnd, int aliveChancePercentage) {
        return rnd.nextDouble() < (double) aliveChancePercentage / 100;
    }
}
