package com.finalprep.gates;

import com.finalprep.interfaces.Gate;

public class NorGate2Input implements Gate {
    public boolean go(boolean a, boolean b) {
        return !(a || b);
    }

    public boolean go(boolean a, boolean b, boolean c) {
        return go(a, b);
    }
}
