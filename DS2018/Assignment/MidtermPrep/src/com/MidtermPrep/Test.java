package com.MidtermPrep;

import com.MidtermPrep.util.ArrayList;
import com.MidtermPrep.util.ArrayStack;
import com.MidtermPrep.util.DoublyLinkedList;
import com.MidtermPrep.util.SinglyLinkedList;

public class Test {
    public static void print(Object dataStructure) {
        if (dataStructure instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing ArrayList ...");
            System.out.println("ArrayList: " + arrayList.toString());
            System.out.println("length: " + arrayList.length());
            System.out.println("currPos: " + arrayList.currPos());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof SinglyLinkedList) {
            SinglyLinkedList singlyLinkedList = (SinglyLinkedList) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing SinglyLinkedList ...");
            System.out.println("SinglyLinkedList: " + singlyLinkedList.toString());
            System.out.println("length: " + singlyLinkedList.length());
            System.out.println("currPos: " + singlyLinkedList.currPos());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof DoublyLinkedList) {
            DoublyLinkedList doublyLinkedList = (DoublyLinkedList) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing DoublyLinkedList ...");
            System.out.println("DoublyLinkedList: " + doublyLinkedList.toString());
            System.out.println("length: " + doublyLinkedList.length());
            System.out.println("currPos: " + doublyLinkedList.currPos());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof ArrayStack) {
            ArrayStack arrayStack = (ArrayStack) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing ArrayStack ...");
            System.out.println("ArrayStack: " + arrayStack.toString());
            System.out.println("length: " + arrayStack.length());
            System.out.println("--------------------------------------------");
            System.out.println();
        }
    }

    public static void test() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(10);
        Test.print(arrayList);

        arrayList.insert(0);
        arrayList.insert(1);
        arrayList.insert(2);
        Test.print(arrayList);

        arrayList.clear();
        Test.print(arrayList);

        arrayList.append(0);
        arrayList.append(1);
        arrayList.append(2);
        Test.print(arrayList);

        arrayList.moveToEnd();
        Test.print(arrayList);

        arrayList.insert(3);
        arrayList.insert(4);
        arrayList.insert(5);
        Test.print(arrayList);

        arrayList.remove();
        arrayList.remove();
        arrayList.remove();
        Test.print(arrayList);

        arrayList.moveToPos(1);
        Test.print(arrayList);

        arrayList.insert(9);
        arrayList.insert(9);
        Test.print(arrayList);

        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<Integer>(10);
        singlyLinkedList.insert(0);
        singlyLinkedList.insert(1);
        singlyLinkedList.insert(2);
        Test.print(singlyLinkedList);

        singlyLinkedList.clear();
        Test.print(singlyLinkedList);

        singlyLinkedList.append(0);
        singlyLinkedList.append(1);
        singlyLinkedList.append(2);
        Test.print(singlyLinkedList);

        singlyLinkedList.moveToEnd();
        Test.print(singlyLinkedList);

        singlyLinkedList.insert(3);
        singlyLinkedList.insert(4);
        singlyLinkedList.insert(5);
        Test.print(singlyLinkedList);

        singlyLinkedList.remove();
        singlyLinkedList.remove();
        singlyLinkedList.remove();
        Test.print(singlyLinkedList);

        singlyLinkedList.moveToPos(1);
        Test.print(singlyLinkedList);

        singlyLinkedList.insert(9);
        singlyLinkedList.insert(9);
        Test.print(singlyLinkedList);

        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>(10);
        doublyLinkedList.insert(0);
        doublyLinkedList.insert(1);
        doublyLinkedList.insert(2);
        Test.print(doublyLinkedList);

        doublyLinkedList.clear();
        Test.print(doublyLinkedList);

        doublyLinkedList.append(0);
        doublyLinkedList.append(1);
        doublyLinkedList.append(2);
        Test.print(doublyLinkedList);

        doublyLinkedList.moveToEnd();
        Test.print(doublyLinkedList);

        doublyLinkedList.insert(3);
        doublyLinkedList.insert(4);
        doublyLinkedList.insert(5);
        Test.print(doublyLinkedList);

        doublyLinkedList.remove();
        doublyLinkedList.remove();
        doublyLinkedList.remove();
        Test.print(doublyLinkedList);

        doublyLinkedList.moveToPos(1);
        Test.print(doublyLinkedList);

        doublyLinkedList.insert(9);
        doublyLinkedList.insert(9);
        Test.print(doublyLinkedList);


    }

}
