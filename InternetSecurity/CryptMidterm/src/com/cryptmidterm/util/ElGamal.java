package com.cryptmidterm.util;

import java.util.Random;

public class ElGamal {
    public int p;
    public int g;
    private int x;
    public int y;

    public ElGamal(int g, int x, int p) {
        this.p = p;
        this.g = g;    
        this.x = x;
        this.y = Modular.powerModP(g, x, p);
    }

    public Tuple<Integer, Integer> encrypt(int M, int publicY, int publicP, int k) {
        Random random = new Random();
        int c1 = Modular.powerModP(g, k, publicP);
        int c2 = (M * Modular.power(publicY, k)) % publicP;
        Tuple<Integer, Integer> tuple = new Tuple<>(c1, c2);
        return tuple;
    }

    public int decrypt(Tuple<Integer, Integer> received) {
        int c1 = received.getFirst();
        int c2 = received.getSecond();
        int yk = Modular.powerModP(c1, x, p);
        int M = Modular.modularDiv(c2, yk, p);
        return M;
    }
}
