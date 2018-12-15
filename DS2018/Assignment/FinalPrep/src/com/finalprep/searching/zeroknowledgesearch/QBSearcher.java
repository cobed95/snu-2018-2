/*
 * QBS is very buggy right now.
 */

package com.finalprep.searching.zeroknowledgesearch;

import com.finalprep.interfaces.Searcher;
import com.finalprep.util.Pair;

public class QBSearcher<V> implements Searcher<Integer, V> {
    private JumpSearcher<Integer, V> jumpSearcher = new JumpSearcher<>();
    private InterpolationSearcher<V> interpolationSearcher = new InterpolationSearcher<>();
    private LinearSearcher<Integer, V> linearSearcher = new LinearSearcher<>();

    public V search(Pair<Integer, V>[] array, Integer key) {
        return search(array, key, 0, array.length - 1);
    }

    public V search(Pair<Integer, V>[] array, Integer key, int start, int end) {
        System.out.println(start + ", " + end);
        if (end - start == 1)
            return linearSearcher.search(array, key, start, end);
        if (start < 0) start = 0;
        if (end >= array.length) end = 99;
        double p = interpolationSearcher.getP(key,
                                              array[start].getKey(),
                                              array[end].getKey());

        int pn = (int) (p * (end - start + 1));
        if (p == 1.0) pn -= 1;
        pn += start;
        System.out.println("pn is " + pn);

        if (key.compareTo(array[pn].getKey()) == 0)
            return array[pn].getValue();
        else {
            int jump;
            Pair<Integer, Integer> pair;
            if (key.compareTo(array[pn].getKey()) < 0) {
                System.out.println("key was smaller.");
                jump = -((int) Math.sqrt(end - start + 1));
                pair = jumpSearcher.searchForSubarray(array, key, pn, start, jump);
            } else {
                System.out.println("kehy was larger.");
                jump = (int) Math.sqrt(end - start + 1);
                pair = jumpSearcher.searchForSubarray(array, key, pn, end, jump);
            }

            return search(array, key, pair.getFirst(), pair.getSecond());
        }
    }
}
