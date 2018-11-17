package com.finalprep;

import com.finalprep.sorting.*;
import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;

import java.util.Random;

public class Test {
    public static Pair<Integer, Integer>[] genRandomArray(int length) {
        Random random = new Random();
        Pair<Integer, Integer>[] array = new Pair[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Pair<Integer, Integer>(random.nextInt(10), i);
        }
        return array;
    }

    public static void testSort(Sorter<Integer, Integer> sorter, Pair<Integer, Integer>[] array) {
        sorter.sort(array);
        ArrayManipulater.print(array);
    }

    public static void testAll() {
        Pair<Integer, Integer>[] array = genRandomArray(10);
        System.out.println("-------- Original Array --------");
        ArrayManipulater.print(array);
        System.out.println();

        System.out.println("-------- Insertion Sort --------");
        Pair<Integer, Integer>[] insertionArray = ArrayManipulater.copy(array);
        testSort(new InsertionSorter<Integer, Integer>(), insertionArray);
        System.out.println();

        System.out.println("-------- Bubble Sort --------");
        Pair<Integer, Integer>[] bubbleArray = ArrayManipulater.copy(array);
        testSort(new BubbleSorter<Integer, Integer>(), bubbleArray);
        System.out.println();

        System.out.println("-------- Selection Sort --------");
        Pair<Integer, Integer>[] selectionArray = ArrayManipulater.copy(array);
        testSort(new SelectionSorter<Integer, Integer>(), selectionArray);
        System.out.println();

        System.out.println("-------- Shell Sort --------");
        Pair<Integer, Integer>[] shellArray = ArrayManipulater.copy(array);
        testSort(new ShellSorter<Integer, Integer>(), shellArray);
        System.out.println();

        System.out.println("-------- Merge Sort --------");
        Pair<Integer, Integer>[] mergeArray = ArrayManipulater.copy(array);
        testSort(new MergeSorter<Integer, Integer>(), mergeArray);
        System.out.println();

        System.out.println("-------- Quick Sort --------");
        Pair<Integer, Integer>[] quickArray = ArrayManipulater.copy(array);
        testSort(new QuickSorter<Integer, Integer>(), quickArray);
        System.out.println();

        System.out.println("-------- Heap Sort --------");
        Pair<Integer, Integer>[] heapArray = ArrayManipulater.copy(array);
        testSort(new HeapSorter<Integer, Integer>(), heapArray);
        System.out.println();
    }

}
