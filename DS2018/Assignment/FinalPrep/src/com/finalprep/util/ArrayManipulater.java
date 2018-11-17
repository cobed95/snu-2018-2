package com.finalprep.util;

import java.util.Arrays;

public class ArrayManipulater {
    public static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <E> void print(E[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static <K extends Comparable<? super K>, V> Pair<K, V>[] copy(Pair<K, V>[] array) {
        Pair<K, V>[] result = new Pair[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }
}
