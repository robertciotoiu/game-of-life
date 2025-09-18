package com.robertciotoiu;

import com.robertciotoiu.model.LifeModel;

import java.util.Random;

public class LifeInitializer {
    public static void rndActivateCells(LifeModel model, int initialAliveChancePercentage) {
        Random rnd = new Random();
        var aliveInitCounter = 0;

        for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                if (shouldBeAliveWithChance(rnd, initialAliveChancePercentage)) {
                    model.activateCell(i, j);
                    aliveInitCounter++;
                }
            }
        }
        System.out.println("Init active cells: " + aliveInitCounter + " / " + model.getRows() * model.getCols());
    }

    private static boolean shouldBeAliveWithChance(Random rnd, int initialAliveChancePercentage) {
        return rnd.nextDouble() < (double) initialAliveChancePercentage / 100;
    }
}
