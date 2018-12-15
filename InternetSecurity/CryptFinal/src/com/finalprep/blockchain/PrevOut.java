package com.finalprep.blockchain;

public class PrevOut {
    public long hash;
    public int n;

    public PrevOut(long hash, int n) {
        this.hash = hash;
        this.n = n;
    }
}
