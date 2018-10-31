package com.cryptmidterm;

import com.cryptmidterm.util.LFSR;

public class Main {

    public static void testLFSR() {
        boolean[] seed = {false, false, true, false};
        boolean[] key = {true, false, false, true};
        LFSR lfsr = new LFSR(seed, key);

        for (int i = 0; i < 8; i++) System.out.println(lfsr.extract());
    }

    public static void main(String[] args) {
        testLFSR();
    }
}
