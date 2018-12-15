package com.finalprep;

import com.finalprep.interfaces.RSLatch;
import com.finalprep.latches.RSLatch2Input;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RSLatch rsLatch = new RSLatch2Input();
        rsLatch.go(false, true);
        rsLatch.go(false, false);
        rsLatch.go(true, false);
        rsLatch.go(false, false);
        rsLatch.go(true, true);
        rsLatch.go(false, false);
    }
}
