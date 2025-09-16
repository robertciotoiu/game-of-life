package com.robertciotoiu.view;

import com.robertciotoiu.api.LifeChangeEvent;
import com.robertciotoiu.api.LifeListener;
import com.robertciotoiu.model.LifeModelImpl;
import com.robertciotoiu.model.LifeModelReadOnly;

import java.awt.*;
import javax.swing.*;


public class LifePanel extends JPanel implements LifeListener {
    LifeModelReadOnly lifeModel;

    private static int CELL_SIZE = 40; // px

    private final int rows;
    private final int cols;
    private final int width;
    private final int height;

    private static final Color lineColor = new Color(200, 200, 200);
    private static final Color aliveColor = new Color(43, 220, 40);
    private static final Color backgroundColor = new Color(235, 235, 235);

    public LifePanel(LifeModelImpl model, int width, int height) {
        lifeModel = model;

        this.rows = lifeModel.getRows();
        this.cols = lifeModel.getCols();

        var cellSizeW = width / cols;
        var cellSizeH = height / rows;
        CELL_SIZE = Math.min(cellSizeH, cellSizeW);

        this.width = width;
        this.height = height;
        setSize(new Dimension(width, height));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height); // e.g. 600x600
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        drawBackground(g2);
        drawCells(g2);
        g2.dispose();
    }

    private void drawCells(Graphics2D g2) {
        var lifeCellsGrid = lifeModel.getCellsGrid();
        var rows = lifeModel.getRows();
        var cols = lifeModel.getCols();
        g2.setColor(aliveColor);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (lifeCellsGrid[i][j]) {
                    g2.fillRect(j * CELL_SIZE + 1, i * CELL_SIZE + 1, CELL_SIZE - 2, CELL_SIZE - 2);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);

        g.setColor(lineColor);
        for (int i = 0; i < height; i = i + CELL_SIZE) {
            g.drawLine(i, 0, i, height);
        }
        for (int i = 0; i < width; i = i + CELL_SIZE) {
            g.drawLine(0, i, width, i);
        }
    }

    @Override
    public void gridChanged(LifeChangeEvent e) {
        repaint();
    }
}