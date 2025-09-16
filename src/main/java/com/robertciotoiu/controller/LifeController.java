package com.robertciotoiu.controller;

import com.robertciotoiu.model.LifeModel;
import com.robertciotoiu.view.LifePanel;

import javax.swing.*;
import java.util.Random;

public class LifeController {
    private static final int REFRESH_RATE = 600; // in millis
    private final LifeModel model;
    private final LifePanel view;

    public LifeController(LifeModel model, LifePanel view, JRootPane rootPane) {
        this.model = model;
        this.view = view;
        this.model.addListener(view);
        init();
        Timer timer = new Timer(REFRESH_RATE, _ -> model.step());
        timer.start();
    }

    private void init() {
        rndActivateCells();
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
        System.out.println("Active cells are: " + aliveInitCounter + " / " + model.getRows() * model.getCols());
    }

    private static boolean shouldBeAliveWithChance(Random rnd, int aliveChancePercentage) {
        return rnd.nextDouble() < (double) aliveChancePercentage / 100;
    }
}
