package com.cryptmidterm.util;

import java.util.Random;

public class RandomOracle {
    private Integer[] table;
    private int size;

    public RandomOracle(int size) {
        table = new Integer[size];
        this.size = size;
    }

    public int hash(int data) {
        while (data >= table.length) {
            expand();
        }
        if (table[data] == null) {
            Random random = new Random();
            table[data] =  random.nextInt(size);
        }
        return table[data];
    }

    private void expand() {
        Integer[] newTable = new Integer[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
        table = newTable;
    }
}
