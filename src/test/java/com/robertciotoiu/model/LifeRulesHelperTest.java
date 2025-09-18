package com.robertciotoiu.model;

public class LifeRulesHelperTest {
    /**
     * Build a state of given size and turn on (row,col) pairs.
     */
    static LifeState stateWith(int rows, int cols, int... rcPairs) {
        LifeState s = new LifeState(cols, rows);
        for (int i = 0; i < rcPairs.length; i += 2) {
            int r = rcPairs[i];
            int c = rcPairs[i + 1];
            s.activateCell(r, c);
        }
        return s;
    }

    /**
     * Advance n generations.
     */
    static LifeState stepN(LifeState s, LifeRules rules, int n) {
        LifeState cur = s;
        for (int i = 0; i < n; i++) cur = rules.step(cur);
        return cur;
    }
}
