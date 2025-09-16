package com.robertciotoiu.model;

import static org.junit.jupiter.api.Assertions.*;

class LifeRulesTest {

    @org.junit.jupiter.api.Test
    void step() {
        LifeRules lifeRules = new LifeRules();
        LifeState lifeState = new LifeState(20, 20);
        var nextLifeState = lifeRules.step(lifeState);

        // build expected life state after step
        var expectedLifeState = new LifeState(lifeState);
//        expectedLifeState.activateCell();
//        expectedLifeState.deactivateCell();

        // assert
        assertEquals(expectedLifeState, nextLifeState);
    }

    // Out of bound tests

    //A living cell with fewer than 2 living neighboring cells dies TEST
    //A living cell with 2 or 3 living neighboring cells stays alive TEST
    //A living cell with more than 3 living neighboring cells dies TEST
    //A dead cell with exactly 3 living neighboring cells comes to life TEST
}