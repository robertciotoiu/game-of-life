package com.robertciotoiu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.robertciotoiu.model.LifeRulesHelperTest.stateWith;
import static org.junit.jupiter.api.Assertions.*;

class LifeRulesBasicTest {

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
    @DisplayName("Single live cell dies of underpopulation")
    void singleCellDiesUnderpopulation() {
        LifeState start = stateWith(20, 20,
                10, 10);
        LifeState next = rules.step(start);
        assertEquals(new LifeState(20, 20), next);
    }

    @Test
    @DisplayName("Single live cell dies of overpopulation")
    void singleCellDiesOverpopulation() {
        LifeState start = stateWith(20, 20,
                0, 0,
                0, 1,
                0, 2,
                1, 0,
                1, 1 // this one have 4 neighbors and should die
        );
        LifeState next = rules.step(start);

        assertTrue(start.getCellsGrid()[1][1], "Cell was alive before step");
        assertFalse(next.getCellsGrid()[1][1], "Cell with 4 neighbors should be dead due to overpopulation");
    }

    @Test
    @DisplayName("Dead cell with exactly three neighbors is born")
    void birthWithThreeNeighbors() {
        LifeState start = stateWith(20, 20,
                10, 9,   // left
                9, 10,   // up
                11, 10);  // down

        LifeState next = rules.step(start);
        assertTrue(next.getCellsGrid()[10][10], "Center cell should be born");
    }

    @Test
    @DisplayName("Cell with exactly two neighbors stays alive")
    void cellWithTwoNeighborsStaysAlive() {
        LifeState start = stateWith(20, 20,
                9, 9,   // upper left
                10, 10,   // center
                11, 11);  // bottom right

        LifeState next = rules.step(start);
        assertTrue(next.getCellsGrid()[10][10], "Cell with upper left and bottom right neighbors should stay alive");
    }

    @Test
    @DisplayName("Cell with exactly three neighbors stays alive")
    void cellWithThreeNeighborsStaysAlive() {
        LifeState start = stateWith(20, 20,
                9, 9,   // upper left
                10, 10,   // center
                10, 9, // left
                11, 11);  // bottom right

        LifeState next = rules.step(start);
        assertTrue(next.getCellsGrid()[10][10], "Cell with upper left, left and bottom right neighbors should stay alive");
    }

    @Test
    @DisplayName("Edge wrapping: corner cell sees neighbors across borders (toroidal)")
    void edgeWrappingWorks() {
        int N = 5;
        // Place three neighbors wrapping around (0,0):
        // (0,4), (4,0), (4,4) → should give (0,0) exactly 3 neighbors => birth
        LifeState start = stateWith(N, N,
                0, 4,
                4, 0,
                4, 4);

        LifeState next = rules.step(start);
        assertTrue(next.getCellsGrid()[0][0], "Corner cell should be born via wrap-around neighbors");
    }

    @Test
    @DisplayName("step() does not mutate input and returns a new instance")
    void stepIsPureAndReturnsNew() {
        LifeState start = stateWith(20, 20,
                10, 10, 10, 11,
                11, 10, 11, 11); // block
        LifeState snapshot = new LifeState(start); // deep copy

        LifeState next = rules.step(start);

        // input unchanged
        assertEquals(snapshot, start, "Input state must remain unchanged by step()");
        // new instance
        assertNotSame(start, next, "step() should return a distinct LifeState");
    }

    @Test
    @DisplayName("step() increments generation")
    void stepIncrementsGeneration() {
        LifeState start = stateWith(20, 20,
                10, 10, 10, 11,
                11, 10, 11, 11);

        LifeState next = rules.step(start);

        assertEquals(start.getGeneration() + 1, next.getGeneration(), "Generation must increment by 1");
    }
}
