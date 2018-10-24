package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.VarBinNode;

public class VarLeafNode implements VarBinNode {
    private String operand;

    public VarLeafNode(String val) {
        operand = val;
    }

    public boolean isLeaf() {
        return true;
    }

    public String value() {
        return operand;
    }
}
