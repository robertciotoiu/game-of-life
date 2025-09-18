package com.robertciotoiu;


import com.robertciotoiu.controller.LifeController;
import com.robertciotoiu.model.LifeModelImpl;
import com.robertciotoiu.view.LifePanel;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeApp {
    private static final int INITIAL_ALIVE_CHANCE_PERCENTAGE = 15;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            var lifeModel = new LifeModelImpl(20, 20);
            var lifeView = new LifePanel(lifeModel, 600, 600);

            // Define status bar
            var statusLabel = new JLabel("Generation: 0 | Alive: 0");
            statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            // Create main Java Frame
            var frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            // Add status bar
            frame.add(statusLabel, BorderLayout.NORTH);
            // Add Game of Life Panel
            frame.add(lifeView, BorderLayout.CENTER);
            frame.pack(); // adjusts frame to fit LifePanel’s preferred size
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);

            // Initialize the cell world randomly
            LifeInitializer.rndActivateCells(lifeModel, INITIAL_ALIVE_CHANCE_PERCENTAGE);

            var lifeController = new LifeController(lifeModel, lifeView, statusLabel);
            lifeController.startGame();
        });
    }
}