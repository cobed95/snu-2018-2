package com.cryptmidterm.util;

import java.util.Random;

public class DiffieHellman {
    private int g;
    private int p;
    private int x;
    private int key;

    public DiffieHellman(int g, int p) {
        this.g = g;
        this.p = p;
        Random random = new Random();
        this.x = random.nextInt(p - 1) + 1;
    }

    public int send() {
        return Modular.powerModP(g, x, p);
    }

    public void receive(int received) {
        this.key = Modular.powerModP(received, x, p);
    }

    public int getKey() {
        return key;
    }

    public void printFields() {
        System.out.println("g: " + g);
        System.out.println("p: " + p);
        System.out.println("x: " + x);
        System.out.println("key: " + key);
    }
}
