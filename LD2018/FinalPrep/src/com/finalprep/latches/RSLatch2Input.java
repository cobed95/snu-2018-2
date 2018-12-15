package com.finalprep.latches;

import com.finalprep.interfaces.RSLatch;
import com.finalprep.gates.NorGate2Input;

public class RSLatch2Input implements RSLatch {
    private NorGate2Input nor0;
    private NorGate2Input nor1;
    private boolean state = false;

    public RSLatch2Input() {
        nor0 = new NorGate2Input();
        nor1 = new NorGate2Input();
    }

    public boolean go(boolean R, boolean S) {
        boolean Q = nor0.go(R, !state);
        state = Q;
        boolean notQ = nor1.go(state, S);
        state = !notQ;
        System.out.println("state is " + state);
        System.out.println("Q is " + Q);
        System.out.println("notQ is " + notQ);
        System.out.println();
        return state;
    }

    public boolean go(boolean R, boolean clk, boolean S) {
        return go(R, S);
    }
}
