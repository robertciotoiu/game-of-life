package com.robertciotoiu.view;

import com.robertciotoiu.api.LifeChangeEvent;
import com.robertciotoiu.api.LifeListener;
import com.robertciotoiu.model.LifeModelReadOnly;

import java.awt.*;
import javax.swing.*;


/**
 * Swing view component for rendering a Game of Life grid.
 *
 * <p>This panel is the "View" in an MVC setup: it observes a
 * {@link LifeModelReadOnly} and repaints itself whenever the model
 * fires a {@link LifeChangeEvent}.</p>
 */
public class LifePanel extends JPanel implements LifeListener {
    private final int rows;
    private final int cols;
    private final int width;
    private final int height;
    private final int cellSize; // in px
    LifeModelReadOnly lifeModel;

    private static final Color lineColor = new Color(200, 200, 200);
    private static final Color aliveColor = new Color(43, 220, 40);
    private static final Color backgroundColor = new Color(235, 235, 235);

    /**
     * Creates a LifePanel for the given model.
     *
     * @param model  the backing model to observe (must implement {@link LifeModelReadOnly})
     * @param width  desired panel width in pixels
     * @param height desired panel height in pixels
     *
     * <p>Cell size is derived automatically from the panel dimensions and
     * the model's row/column count to preserve square cells.</p>
     */
    public LifePanel(LifeModelReadOnly model, int width, int height) {
        lifeModel = model;

        this.rows = lifeModel.getRows();
        this.cols = lifeModel.getCols();

        var cellSizeW = width / cols;
        var cellSizeH = height / rows;
        cellSize = Math.min(cellSizeH, cellSizeW);

        this.width = width;
        this.height = height;
        setSize(new Dimension(width, height));
    }

    // Called when the model updates; trigger a repaint of the panel.
    @Override
    public void gridChanged(LifeChangeEvent e) {
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
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

    // Fill rectangles for all live cells.
    private void drawCells(Graphics2D g2) {
        var lifeCellsGrid = lifeModel.getCellsGrid();
        var rows = lifeModel.getRows();
        var cols = lifeModel.getCols();
        g2.setColor(aliveColor);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (lifeCellsGrid[i][j]) {
                    // Offsets (+1, -2) used to create grid spacing between cells.
                    g2.fillRect(j * cellSize + 1, i * cellSize + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
    }

    // Paints the background and draws grid lines.
    private void drawBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);

        g.setColor(lineColor);
        for (int i = 0; i < height; i = i + cellSize) {
            g.drawLine(i, 0, i, height);
        }
        for (int i = 0; i < width; i = i + cellSize) {
            g.drawLine(0, i, width, i);
        }
    }
}