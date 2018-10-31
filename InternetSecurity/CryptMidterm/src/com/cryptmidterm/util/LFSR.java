package com.cryptmidterm.util;

public class LFSR {
    private boolean[] stages;
    private boolean[] key;

    public LFSR(boolean[] seed, boolean[] c) {
        assert seed.length == c.length : "LFSR seed and key length different.";
        stages = seed;
        key = c;
    }

    public boolean extract() {
        boolean result = stages[0];
        boolean newTail = stages[0] & key[0];
        for (int i = 1; i < stages.length; i++) {
            newTail = newTail ^ (stages[i] & key[0]);
        }
        for (int i = 0; i < stages.length - 1; i++) {
            stages[i] = stages[i + 1];
        }
        stages[stages.length - 1] = newTail;
        return result;
    }
}
