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

            var frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(grid, BorderLayout.CENTER);

            // add toolbar/status if you have them
            new LifeController(model, grid, frame.getRootPane());

            frame.pack(); // <-- adjusts frame to fit LifePanel’s preferred size
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}