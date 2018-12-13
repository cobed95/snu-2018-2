package com.finalprep.blockchain;

import java.util.Stack;

public class Interpreter {
    public Stack<String> stack;
    public String[] script;

    public Interpreter(String[] scriptSig, String[] scriptPubKey) {
        stack = new Stack<String>();

        script = new String[7];

        for (int i = 0; i < 2; i++)
            script[i] = scriptSig[i];

        for (int i = 0; i < 5; i++)
            script[i + 2] = scriptPubKey[i];
    }

    public void run() {

        int ptr = 0;
        while (ptr < 7) {
            System.out.println("Processing script: " + script[ptr]);
            switch (script[ptr]) {
                case ("DUP") :
                    String dup = stack.peek();
                    stack.push(dup);
                    System.out.println("Pushed " + dup + " to stack.");
                    break;
                case ("HASH160") :
                    String pubK = stack.peek();
                    Integer hash = pubK.hashCode();
                    stack.push(hash.toString());
                    System.out.println("Pushed " + hash + " to stack.");
                    break;
                case ("EQUALVERIFY") :
                    String hash1 = stack.pop();
                    System.out.println("Popped " + hash1 + " from stack.");
                    String hash2 = stack.pop();
                    System.out.println("Popped " + hash2 + " from stack.");
                    if (hash1.equals(hash2)) {
                        stack.push("TRUE");
                        System.out.println("Pushed TRUE to stack.");
                    } else {
                        stack.push("FALSE");
                        System.out.println("Pushed FALSE to stack.");
                    }
                    break;
                case ("CHECKSIG") :
                    String prevResult = stack.pop();
                    System.out.println("Popped " + prevResult + " from stack.");
                    if (prevResult.equals("TRUE")) {
                        String pubk = stack.pop();
                        System.out.println("Popped " + pubk + " from stack.");
                        String sig = stack.pop();
                        System.out.println("Popped " + sig + " from stack.");
                        if (sig.equals(pubk)) {
                            stack.push("TRUE");
                            System.out.println("Pushed TRUE to stack.");
                        } else {
                            stack.push("FALSE");
                            System.out.println("Pushed FALSE to stack.");
                        }
                    } else {
                        stack.pop();
                        System.out.println("Popped from stack.");
                        stack.push("FALSE");
                        System.out.println("Pushed FALSE to stack.");
                    }
                    break;
                default :
                    stack.push(script[ptr]);
                    System.out.println("Pushed " + script[ptr] + " to stack.");
            }
            ptr++;
        }

        String result = stack.pop();
        if (result == "TRUE")
            System.out.println("Transaction is valid.");
        else
            System.out.println("Transaction is invalid.");
    }
}
