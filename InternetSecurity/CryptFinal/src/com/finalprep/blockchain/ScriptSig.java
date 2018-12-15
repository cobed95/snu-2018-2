package com.finalprep.blockchain;

public class ScriptSig {
    public String[] script;

    public ScriptSig(String sig, String pubK) {
        script = new String[2];
        script[0] = sig;
        script[1] = pubK;
    }
}
