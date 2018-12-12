package com.finalprep.interfaces;

public interface RSLatch {
    public boolean go(boolean R, boolean S);
    public boolean go(boolean R, boolean clk, boolean S);
}
