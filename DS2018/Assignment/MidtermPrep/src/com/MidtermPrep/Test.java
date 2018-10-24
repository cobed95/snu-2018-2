package com.MidtermPrep;

import com.MidtermPrep.interfaces.*;
import com.MidtermPrep.util.*;

import java.util.Random;

public class Test {

    public static void test() {
        List<Integer> arrayList = new ArrayList<Integer>(10);
        List<Integer> singlyLinkedList = new SinglyLinkedList<Integer>();
        List<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();
        testList(arrayList);
        testList(singlyLinkedList);
        testList(doublyLinkedList);

        Stack<Integer> arrayStack = new ArrayStack<Integer>(10);
        Stack<Integer> linkedStack = new LinkedStack<Integer>();
        testStack(arrayStack);
        testStack(linkedStack);

        Queue<Integer> circularQueue = new CircularQueue<Integer>(10);
        testQueue(circularQueue);

        Dictionary<Integer, String> unsortedDictionary = new UnsortedDictionary<Integer, String>();
        testDictionary(unsortedDictionary);

        BinaryNode<Integer> simpleBinaryNode =
                new SimpleBinaryNode<Integer>(0,
                        new SimpleBinaryNode<Integer>(1,
                                new SimpleBinaryNode<Integer>(2),
                                new SimpleBinaryNode<Integer>(3)),
                        new SimpleBinaryNode<Integer>(4,
                                new SimpleBinaryNode<Integer>(5),
                                new SimpleBinaryNode<Integer>(6)));
        testBinaryNode(simpleBinaryNode);

        VarBinNode varBinNode = new VarIntlNode('-',
                                        new VarIntlNode('*',
                                                new VarIntlNode('*',
                                                        new VarLeafNode("4"),
                                                        new VarLeafNode("x")),
                                                new VarIntlNode('+',
                                                        new VarIntlNode('*',
                                                                new VarLeafNode("2"),
                                                                new VarLeafNode("x")),
                                                        new VarLeafNode("a"))),
                                        new VarLeafNode("c"));
        testVarBinaryNode(varBinNode);
    }

    public static void testList(List<Integer> list) {
        Test.print(list);

        list.insert(0);
        list.insert(1);
        list.insert(2);
        Test.print(list);

        list.clear();
        Test.print(list);

        list.append(0);
        list.append(1);
        list.append(2);
        Test.print(list);

        list.moveToEnd();
        Test.print(list);

        list.insert(3);
        list.insert(4);
        list.insert(5);
        Test.print(list);

        list.remove();
        list.remove();
        list.remove();
        Test.print(list);

        list.moveToPos(1);
        Test.print(list);

        list.insert(9);
        list.insert(9);
        Test.print(list);
    }

    public static void testStack(Stack<Integer> stack) {
        print(stack);

        stack.push(0);
        stack.push(1);
        stack.push(2);
        print(stack);

        stack.pop();
        stack.pop();
        print(stack);
    }

    public static void testQueue(Queue<Integer> queue) {
        print(queue);

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        print(queue);

        queue.clear();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        print(queue);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        print(queue);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        print(queue);
    }

    public static void testDictionary(Dictionary<Integer, String> dictionary) {
        print(dictionary);

        dictionary.insert(0, "Alice");
        dictionary.insert(1, "Bob");
        dictionary.insert(2, "Charlie");
        print(dictionary);

        dictionary.clear();
        Random random = new Random();
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(10);
            int b = random.nextInt(10);
            int temp = numbers[a];
            numbers[a] = numbers[b];
            numbers[b] = temp;
        }

        for (int i = 0; i < 10; i++) {
            dictionary.insert(numbers[i], Integer.toString(numbers[i]));
        }
        print(dictionary);

        for (int i = 0; i < 10; i++) {
            System.out.println("Found " + i + ": " + dictionary.find(i));
        }

        dictionary.remove(9);
        print(dictionary);
    }

    public static void testBinaryNode(BinaryNode<Integer> binaryNode) {
        System.out.println("preorder: " + SimpleBinaryNode.preorder(binaryNode));
        System.out.println("inorder: " + SimpleBinaryNode.inorder(binaryNode));
        System.out.println("postorder: " + SimpleBinaryNode.postorder(binaryNode));
    }

    public static void testVarBinaryNode(VarBinNode varBinNode) {
        System.out.println("inorder: " + VarIntlNode.inorder(varBinNode));
    }

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
        } else if (dataStructure instanceof LinkedStack) {
            LinkedStack linkedStack = (LinkedStack) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing LinkedStack ...");
            System.out.println("LinkedStack: " + linkedStack.toString());
            System.out.println("length: " + linkedStack.length());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof CircularQueue) {
            CircularQueue circularQueue = (CircularQueue) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing CircularQueue ...");
            System.out.println("CircularQueue: " + circularQueue.toString());
            System.out.println("length: " + circularQueue.length());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof UnsortedDictionary) {
            UnsortedDictionary unsortedDictionary = (UnsortedDictionary) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing UnsortedDictionary ...");
            System.out.println("UnsortedDictionary: " + unsortedDictionary.toString());
            System.out.println("size: " + unsortedDictionary.size());
            System.out.println("--------------------------------------------");
            System.out.println();
        } else if (dataStructure instanceof BinaryNode) {
            BinaryNode binaryNode = (BinaryNode) dataStructure;
            System.out.println("____________________________________________");
            System.out.println("Printing BinaryNode ...");
            //System.out.println("BinaryNode: " + binaryNode.toString());
            System.out.println("--------------------------------------------");
            System.out.println();
        }
    }
}
