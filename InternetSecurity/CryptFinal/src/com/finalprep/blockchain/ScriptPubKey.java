package com.finalprep.blockchain;

public class ScriptPubKey {
    public String[] script;

    public ScriptPubKey(Integer pubKHash) {
        script = new String[5];

        script[0] = "DUP";
        script[1] = "HASH160";
        script[2] = pubKHash.toString();
        script[3] = "EQUALVERIFY";
        script[4] = "CHECKSIG";
    }
}
