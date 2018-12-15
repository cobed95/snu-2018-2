package com.finalprep;

import com.finalprep.graph.BasicGraph;
import com.finalprep.interfaces.Graph;
import com.finalprep.interfaces.Sorter;
import com.finalprep.searching.zeroknowledgesearch.QBSearcher;
import com.finalprep.sorting.*;
import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;
import com.finalprep.graph.GraphTraverser;

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

    public static void testAllSort() {
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

        System.out.println("-------- Bin Sort --------");
        Pair<Integer, Integer>[] binArray = ArrayManipulater.copy(array);
        testSort(new BinSorter<Integer>(), binArray);
        System.out.println();

        System.out.println("-------- Radix Sort --------");
        Pair<Integer, Integer>[] radixArray = new Pair[10];
        radixArray[0] = new Pair<Integer, Integer>(27, 0);
        radixArray[1] = new Pair<Integer, Integer>(91, 1);
        radixArray[2] = new Pair<Integer, Integer>(1, 2);
        radixArray[3] = new Pair<Integer, Integer>(97, 3);
        radixArray[4] = new Pair<Integer, Integer>(17, 4);
        radixArray[5] = new Pair<Integer, Integer>(23, 5);
        radixArray[6] = new Pair<Integer, Integer>(84, 6);
        radixArray[7] = new Pair<Integer, Integer>(28, 7);
        radixArray[8] = new Pair<Integer, Integer>(72, 8);
        radixArray[9] = new Pair<Integer, Integer>(5, 9);
        radixArray[0] = new Pair<Integer, Integer>(67, 10);
        radixArray[0] = new Pair<Integer, Integer>(25, 11);
        testSort(new RadixSorter<Integer>(), radixArray);
    }

    public static void testSearch() {
        System.out.println("-------- QBS --------");

        Random random = new Random();
        Pair<Integer, Integer>[] qbsArray = new Pair[100];
        for (int i = 0; i < 100; i++) {
            int keyValue = random.nextInt(2000);
            qbsArray[i] = new Pair<Integer, Integer>(keyValue, keyValue);
        }
        QuickSorter<Integer, Integer> quickSorter = new QuickSorter<>();
        quickSorter.sort(qbsArray);

        QBSearcher<Integer> qbs = new QBSearcher<Integer>();

        System.out.println();
        for (Pair<Integer, Integer> pair : qbsArray) {
            System.out.println("Searching for: " + pair.getKey());
            Integer value = qbs.search(qbsArray, pair.getKey());
            System.out.println("Found: " + value);
            System.out.println();
        }
    }

    public static void testTopSort() {

        Graph graph = new BasicGraph();
        graph.Init(14);
        graph.setEdge(0, 4, 1);
        graph.setEdge(0, 5, 1);
        graph.setEdge(0, 11, 1);

        graph.setEdge(1, 2, 1);
        graph.setEdge(1, 4, 1);
        graph.setEdge(1, 8, 1);

        graph.setEdge(2, 5, 1);
        graph.setEdge(2, 6, 1);
        graph.setEdge(2, 9, 1);

        graph.setEdge(3, 2, 1);
        graph.setEdge(3, 6, 1);
        graph.setEdge(3, 13, 1);

        graph.setEdge(4, 7, 1);

        graph.setEdge(5, 8, 1);
        graph.setEdge(5, 12, 1);

        graph.setEdge(6, 5, 1);

        graph.setEdge(8, 7, 1);

        graph.setEdge(9, 10, 1);
        graph.setEdge(9, 11, 1);

        graph.setEdge(10, 13, 1);

        graph.setEdge(12, 9, 1);

        GraphTraverser graphTraverser = new GraphTraverser();

        graphTraverser.topologicalSort(graph);
    }

}
