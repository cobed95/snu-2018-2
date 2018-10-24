package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Dictionary;

public class UnsortedDictionary<Key, E> implements Dictionary<Key, E> {
    private ArrayList<KVpair<Key, E>> arrayList;

    public UnsortedDictionary() {
        arrayList = new ArrayList<KVpair<Key, E>>();
    }

    public UnsortedDictionary(int size) {
        arrayList = new ArrayList<KVpair<Key, E>>(size);
    }

    public void clear() {
        arrayList.clear();
    }

    public void insert(Key k, E e) {
        arrayList.append(new KVpair<Key, E>(k, e));
    }

    public E remove(Key k) {
        arrayList.moveToStart();
        while (arrayList.currPos() != arrayList.length()
            && arrayList.getValue().key() != k) {
            arrayList.next();
        }
        if (arrayList.currPos() == arrayList.length()) return null;
        else return arrayList.remove().value();
    }

    public E removeAny() {
        if (arrayList.length() == 0) return null;
        if (arrayList.getValue() == null) arrayList.moveToStart();
        return arrayList.remove().value();
    }

    public E find(Key k) {
        arrayList.moveToStart();
        while (arrayList.currPos() != arrayList.length()
            && arrayList.getValue().key() != k) {
            arrayList.next();
        }
        if (arrayList.currPos() == arrayList.length()) return null;
        else return arrayList.getValue().value();
    }

    public int size() {
        return arrayList.length();
    }

    public String toString() {
        String result = "\n";
        arrayList.moveToStart();
        for (int i = 0; i < arrayList.length(); i++) {
            result += arrayList.getValue() + "\n";
            arrayList.next();
        }
        return result;
    }
}
