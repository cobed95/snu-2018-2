package com.finalprep;

import com.finalprep.blockchain.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Metadata metadata = new Metadata();
        metadata.version = "1.0";
        metadata.vin_sz = 1;
        metadata.vout_sz = 1;
        metadata.size = 404;

        Inputs inputs = new Inputs(1);
        inputs.setPrevOut(0, new PrevOut(10000000, 0));
        ScriptSig scriptSig = new ScriptSig("exampleKey", "exampleKey");
        inputs.setScriptSig(0, scriptSig);

        Transaction transaction = new Transaction(metadata, inputs);

        Verifier verifier = new Verifier(transaction);
        verifier.verify();
    }
}
