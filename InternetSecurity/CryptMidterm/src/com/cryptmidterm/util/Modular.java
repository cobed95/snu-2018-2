package com.cryptmidterm.util;

public class Modular {
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static boolean isRelativelyPrime(int a, int b) {
        return gcd(a, b) == 1;
    }

    public static int power(int base, int exponent) {
        if (exponent <= 0) return 1;
        else if (exponent % 2 == 0) return power(base, exponent / 2) * power(base, exponent / 2);
        else return base * power(base, exponent / 2) * power(base, exponent / 2);
    }

    public static int powerModP(int base, int exponent, int p) {
        return power(base, exponent) % p;
    }

    public static int modularDiv(int dividend, int divisor, int p) {
        assert divisor != 0 && divisor % p != 0 : "Error: division by 0.";
        int quotient = 1;
        while ((divisor * quotient) % p != dividend % p) {
            quotient++;
        }
        return quotient;
    }
}
