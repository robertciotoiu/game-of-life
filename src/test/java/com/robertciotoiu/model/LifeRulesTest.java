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
}