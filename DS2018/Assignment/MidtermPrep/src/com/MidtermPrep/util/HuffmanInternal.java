package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.HuffmanNode;

public class HuffmanInternal<F, T> implements HuffmanNode<F, T> {
    private F weight;
    private HuffmanNode<F, T> left;
    private HuffmanNode<F, T> right;

    public HuffmanInternal(F w, HuffmanNode<F, T> l, HuffmanNode<F, T> r) {
        weight = w;
        left = l;
        right = r;
    }

    public boolean isLeaf() {
        return false;
    }

    public F getWeight() {
        return weight;
    }
}
