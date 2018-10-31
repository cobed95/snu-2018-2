package com.cryptmidterm.util;

import com.cryptmidterm.util.arrayManipulation;

public class DES {
    private boolean[] permuteInit(boolean[] input, boolean isInit) {
        boolean[] result = new boolean[64];
        int offset = 0;
        int start = 57;
        while (offset < 64) {
            for (int i = offset; i < offset + 8; i++) {
                for (int j = start; j >= 0; start -= 8) {
                    if (isInit) result[i] = input[start];
                    else result[start] = input[i];
                }
            }
            if (start == 63) start = 56;
            else start += 2;
            offset += 8;
        }
        return result;
    }

    private boolean[] expand(boolean[] input, boolean isInit) {
        boolean[] result = new boolean[48];
        return result;
    }

    private boolean[] round(boolean[] input) {
        boolean[] result = new boolean[64];
        boolean[] left = arrayManipulation.slice(input, 0, 32);
        boolean[] right = arrayManipulation.slice(input, 32, 64);

    }

}
