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
        if (exponent == 0) return 1;
        else if (exponent % 2 == 0) return power(base, exponent / 2) * power(base, exponent / 2);
        else return base * power(base, exponent / 2) * power(base, exponent / 2);
    }

    public static int mod(int m, int n) {
        while (m > n) {
            m -= n;
        }
        return m;
    }

    public static int powerModP(int base, int exponent, int p) {
        if (exponent == 0) return 1;
        else return mod(base * powerModP(base, exponent - 1, p), p);
    }

    public static int modularDiv(int dividend, int divisor, int p) {
        assert divisor != 0 && mod(divisor, p) != 0 : "Error: division by 0.";
        int quotient = 1;
        while (mod((divisor * quotient), p) != mod(dividend, p)) {
            quotient++;
        }
        return quotient;
    }

    public static int bitwiseXor(int a, int b) {
        String aBin = Integer.toBinaryString(a);
        String bBin = Integer.toBinaryString(b);
        if (aBin.length() != bBin.length()) return -1;
        else {
            String result = "";
            for (int i = 0; i < aBin.length(); i++) {
                if (aBin.charAt(i) == bBin.charAt(i)) result += "0";
                else result += "1";
            }

            int resultInInt = 0;
            for (int i = result.length() - 1; i >= 0; i--) {
                if (result.charAt(i) == '1')
                    resultInInt += Modular.power(2, result.length() - 1 - i);
            }
            return resultInInt;
        }
    }

}
