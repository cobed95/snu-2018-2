package com.finalprep.blockchain;

import java.util.Date;

public class Verifier {
    public Transaction transaction;
    public Interpreter interpreter;

    public Verifier(Transaction transaction) {
        this.transaction = transaction;
        String[] scriptSig = transaction.inputs.scriptSigs[0].script;
        String[] scriptPubKey = transaction.outputs.scriptPubKeys[0].script;
        interpreter = new Interpreter(scriptSig, scriptPubKey);
    }

    public void verify() {
        System.out.println("Running verification...");
        System.out.println();
        System.out.println("Transaction hash: " + transaction.hash);
        System.out.println();
        System.out.println("Printing metadata...");
        System.out.println("Version: " + transaction.metadata.version);
        System.out.println("vin_sz: " + transaction.metadata.vin_sz);
        System.out.println("vout_sz: " + transaction.metadata.vout_sz);
        System.out.println("lock_time: " + new Date().toString());
        System.out.println("size: " + transaction.metadata.size);
        System.out.println();
        System.out.println("Running interpreter...");
        interpreter.run();
        System.out.println("Interpreter finished.");
    }
}
