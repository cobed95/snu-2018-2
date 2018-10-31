package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.HuffmanNode;

public class HuffmanLeaf<F, T> implements HuffmanNode<F, T> {
    private F frequency;
    private T character;

    public HuffmanLeaf(F freq, T c) {
        frequency = freq;
        character = c;
    }

    public boolean isLeaf() {
        return true;
    }

    public F getWeight() {
        return frequency;
    }

    public T getCharacter() {
        return character;
    }
}
