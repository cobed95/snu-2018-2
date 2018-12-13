package com.finalprep.blockchain;

public class Outputs {
    public double[] values;
    public ScriptPubKey[] scriptPubKeys;

    public Outputs(int vout_sz) {
        values = new double[vout_sz];
        scriptPubKeys = new ScriptPubKey[vout_sz];
    }
}
