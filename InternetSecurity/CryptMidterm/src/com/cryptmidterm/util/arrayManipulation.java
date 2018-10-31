package com.cryptmidterm.util;

public class arrayManipulation {
    public static boolean[] slice(boolean[] array, int from, int to) {
        boolean[] result = new boolean[to - from];
        for (int i = from; i < to; i++) {
            result[i - from] = array[i];
        }
        return result;
    }

    public static boolean[] concat(boolean[] left, boolean[] right) {
        boolean[] result = new boolean[left.length + right.length];
        for (int i = 0; i < left.length; i++) result[i] = left[i];
        for (int i = 0; i < right.length; i++) result[i + left.length] = right[i];
        return result;
    }
}
