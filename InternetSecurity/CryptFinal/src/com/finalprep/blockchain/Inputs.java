package com.finalprep.blockchain;

public class Inputs {
    public PrevOut[] prevOuts;
    public ScriptSig[] scriptSigs;

    public Inputs(int vin_sz) {
        prevOuts = new PrevOut[vin_sz];
        scriptSigs = new ScriptSig[vin_sz];
    }

    public void setPrevOut(int i, PrevOut prevOut) {
        prevOuts[i] = prevOut;
    }

    public void setScriptSig(int i, ScriptSig scriptSig) {
        scriptSigs[i] = scriptSig;
    }
}
