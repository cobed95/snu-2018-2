package com.cryptmidterm;

import com.cryptmidterm.util.LFSR;
import com.cryptmidterm.util.RSA;

public class Main {

    public static void testLFSR() {
        boolean[] seed = {false, false, true, false};
        boolean[] key = {true, false, false, true};
        LFSR lfsr = new LFSR(seed, key);

        for (int i = 0; i < 8; i++) System.out.println(lfsr.extract());
    }

    public static void testRSA() {
        RSA rsa = new RSA(17, 11);
        rsa.printFields();
        System.out.println(rsa.encrypt(88));
    }

    public static void main(String[] args) {
        testRSA();
    }
}
