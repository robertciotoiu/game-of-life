package com.robertciotoiu.api;

/**
 * Observer interface for receiving notifications when a model
 * changes state.
 */
public interface LifeListener {
    void gridChanged(LifeChangeEvent e);
}
