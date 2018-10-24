package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.VarBinNode;

public class VarIntlNode implements VarBinNode {
    private VarBinNode left;
    private VarBinNode right;
    private Character operator;

    public VarIntlNode(Character op, VarBinNode l, VarBinNode r) {
        operator = op; left = l; right = r;
    }

    public boolean isLeaf() {
        return false;
    }

    public VarBinNode leftChild() {
        return left;
    }

    public VarBinNode rightChild() {
        return right;
    }

    public Character value() {
        return operator;
    }

    public static String inorder(VarBinNode root) {
        return inorder(root, "");
    }

    private static String inorder(VarBinNode root, String result) {
        if (root instanceof VarLeafNode) return result + ((VarLeafNode) root).value();
        result = inorder(((VarIntlNode) root).leftChild(), result);
        result += ((VarIntlNode) root).value();
        result = inorder(((VarIntlNode) root).rightChild(), result);
        return result;
    }
}
