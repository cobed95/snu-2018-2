package com.cryptmidterm.util;

import java.util.Random;

public class RSA {
    private int keyU;
    private int keyR;
    private int p;
    private int q;
    
    public RSA(int p, int q) {
        this.p = p;
        this.q = q;
        findPublic();
        calcPrivate();
    }

    private int gcd(int a, int b){
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    private void findPublic() {
        Random random = new Random();
        int totient = (p - 1) * (q - 1);
        int candidate = random.nextInt(totient);
        while (gcd(candidate, totient) != 1) {
            candidate = random.nextInt(totient);
        }
        this.keyU = candidate;
    }

    private void calcPrivate() {
        int totient = (p - 1) * (q - 1);
        keyR = calcMultInv(keyU, totient);
    }

    private int calcMultInv(int e, int N) {
        int candidate = 1;
        while ((e * candidate) % N != 1) {
            candidate++;
        }
        return candidate;
    }

    public int encrypt(int m) {
        System.out.println(m);
        System.out.println(keyR);
        System.out.println(Math.pow(m, keyR));
        System.out.println((p * q));
        return (int) (Math.pow(m, keyR)) % (p * q);
    }

    private int decrypt(int c) {
        int c1 = c % p;
        int c2 = c % q;
        int m1 = Math.pow(c1, keyR);
        int m2 = Math.pow(c2, keyR);
        return solveCRT(m1, m2);
    }

    private int solveCRT(int a1, int a2) {
        int M1 = q;
        int M2 = p;
        int y1 = calcMultInv(M1, p);
        int y2 = calcMultInv(M2, q);
        return ((a1 * y1 * M1) + (a2 * y2 * M2)) % (p * q);
    }

    public void printFields() {
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("N: " + (p * q));
        System.out.println("totient: " + ((p - 1) * (q - 1)));
        System.out.println("public: " + keyU);
        System.out.println("private: " + keyR);
    }
}