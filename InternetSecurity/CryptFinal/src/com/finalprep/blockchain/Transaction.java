package com.finalprep.blockchain;

public class Transaction {
    public long hash;
    public Metadata metadata;
    public Inputs inputs;
    public Outputs outputs;

    public Transaction(Metadata metadata, Inputs inputs) {
        this.metadata = metadata;
        this.inputs = inputs;
        createOutputs();
    }

    private void createOutputs() {
        outputs = new Outputs(metadata.vout_sz);
        for (int i = 0; i < metadata.vout_sz; i++) {
            outputs.scriptPubKeys[0] = new ScriptPubKey(inputs.scriptSigs[0].script[1].hashCode());
        }
    }
}
