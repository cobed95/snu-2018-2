package com.finalprep.sorting;

import com.finalprep.interfaces.Sorter;
import com.finalprep.util.Pair;

import com.finalprep.util.ArrayManipulater;

import java.util.Random;

public class QuickSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {

    public void sort(Pair<K, V>[] array) {
        sort(array, 0, array.length - 1);
    }

    public void sort(Pair<K, V>[] array, int left, int right) {
        int pivotIdx = getPivot(left, right);
        ArrayManipulater.swap(array, pivotIdx, right);
        int mid = partition(array, left - 1, right, array[right]);
        ArrayManipulater.swap(array, mid, right);
        if ((mid - left) > 1) sort(array, left, mid - 1);
        if ((right - mid) > 1) sort(array, mid + 1, right);
    }

    public int getPivot(int left, int right) {
        return (left + right) / 2;
    }

    public int partition(Pair<K, V>[] array, int left, int right, Pair<K, V> pivot) {
        do {
            while (array[++left].getKey().compareTo(pivot.getKey()) < 0);
            while (right != 0 && array[--right].getKey().compareTo(pivot.getKey()) > 0);
            ArrayManipulater.swap(array, left, right);
        } while (left < right);
        ArrayManipulater.swap(array, left, right);
        return left;
    }

    //public void sort(Pair<K, V>[] array) {
    //    sort(array, 0, array.length - 1);
    //}

    //public void sort(Pair<K, V>[] array, int left, int right) {
    //    int pivotIdx = getPivot(left, right);
    //    ArrayManipulater.swap(array, pivotIdx, right);
    //    int mid = partition(array, left - 1, right, array[right]);
    //    ArrayManipulater.swap(array, mid, right);
    //    if ((mid - left) > 1) sort(array, left, mid - 1);
    //    if ((right - mid) > 1) sort(array, mid + 1, right);
    //}

    //public int getPivot(int i, int j) {
    //    return (i + j) / 2;
    //}

    //public int partition(Pair<K, V>[] array, int left, int right, Pair<K, V> pivot) {
    //    do {
    //        while (array[++left].getKey().compareTo(pivot.getKey()) < 0);
    //        while (right != 0 && array[--right].getKey().compareTo(pivot.getKey()) > 0);
    //        ArrayManipulater.swap(array, left, right);
    //    } while (left < right);
    //    ArrayManipulater.swap(array, left, right);
    //    return left;
    //}
}
