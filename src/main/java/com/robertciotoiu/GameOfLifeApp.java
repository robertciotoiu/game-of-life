package com.robertciotoiu;


import com.robertciotoiu.controller.LifeController;
import com.robertciotoiu.model.LifeModelImpl;
import com.robertciotoiu.view.LifePanel;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            /**
             * Create the GUI and show it.  For thread safety,
             * this method is invoked from the
             * event dispatch thread.
             */
            var model = new LifeModelImpl(20, 20);
            var grid  = new LifePanel(model, 600, 600);

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
            frame.add(grid, BorderLayout.CENTER);
            frame.pack(); // adjusts frame to fit LifePanel’s preferred size
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);

            var lifeController = new LifeController(model, grid, statusLabel);
            lifeController.startGame();
        });
    }
}