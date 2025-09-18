package com.robertciotoiu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.robertciotoiu.model.LifeRulesHelperTest.stateWith;
import static com.robertciotoiu.model.LifeRulesHelperTest.stepN;
import static org.junit.jupiter.api.Assertions.*;

class LifeRulesPatternsTest {

    private LifeRules rules;

    @BeforeEach
    void setUp() {
        rules = new LifeRules();
    }

    @Test
    @DisplayName("Empty world stays empty")
    void emptyWorldStaysEmpty() {
        LifeState start = new LifeState(20, 20);
        LifeState next = rules.step(start);
        assertEquals(start, next);
    }

    @Test
    @DisplayName("Block is a still life (unchanged across steps)")
    void blockStillLife() {
        // 2x2 block at (10,10)
        LifeState start = stateWith(20, 20,
                10, 10,
                10, 11,
                11, 10,
                11, 11);

        LifeState next = rules.step(start);
        assertEquals(start, next);

        LifeState after5 = stepN(start, rules, 5);
        assertEquals(start, after5);
    }

    @Test
    @DisplayName("Blinker oscillates with period 2")
    void blinkerOscillates() {
        // Horizontal blinker centered at (10,10)
        LifeState start = stateWith(20, 20,
                10, 9,
                10, 10,
                10, 11);

        LifeState expectedAfter1 = stateWith(20, 20,
                9, 10,
                10, 10,
                11, 10); // vertical

        assertEquals(expectedAfter1, rules.step(start));
        assertEquals(start, stepN(start, rules, 2));
    }

    @Test
    @DisplayName("Glider moves diagonally after 4 steps")
    void gliderMoves() {
        LifeState start = stateWith(20, 20,
        6,6,
        5,6,
        4,6,
        6,5,
        5,4);
        LifeState after4 = stepN(start, rules, 4);
        LifeState expected = stateWith(20, 20,
                7,7,
                6,7,
                5,7,
                7,6,
                6,5);
        assertEquals(expected, after4);
    }
}
