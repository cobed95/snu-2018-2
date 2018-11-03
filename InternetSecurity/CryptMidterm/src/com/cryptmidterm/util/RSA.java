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
        while (Modular.mod((e * candidate), N) != 1) {
            candidate++;
        }
        return candidate;
    }

    public int encryptWithPriv(int m) {
        return Modular.powerModP(m, keyR, (p * q));
    }

    public int encryptWithPub(int m, int N, int pubKey) {
        return Modular.powerModP(m, pubKey, N);
    }

    public int decryptWithPriv(int c) {
        int c1 = Modular.mod(c, p);
        int c2 = Modular.mod(c, q);
        int m1 = Modular.power(c1, keyR);
        int m2 = Modular.power(c2, keyR);
        return solveCRT(m1, m2);
    }

    public int decryptWithPub(int c, int N, int pubKey) {
        return Modular.powerModP(c, pubKey, N);
    }

    public int getPublicKey() {
        return keyU;
    }

    public int getN() {
        return p * q;
    }

    private int solveCRT(int a1, int a2) {
        int M1 = q;
        int M2 = p;
        int y1 = calcMultInv(M1, p);
        int y2 = calcMultInv(M2, q);
        return Modular.mod(((a1 * y1 * M1) + (a2 * y2 * M2)), (p * q));
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