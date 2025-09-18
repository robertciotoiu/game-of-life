package com.robertciotoiu.api;

/**
 * Event object representing a change in the Game of Life model.
 */
public record LifeChangeEvent(int minRow, int minCol, int maxRow, int maxCol) {
    public static LifeChangeEvent all() {
        return new LifeChangeEvent(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
