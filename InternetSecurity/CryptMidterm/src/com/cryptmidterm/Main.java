package com.cryptmidterm;

import com.cryptmidterm.util.*;
import com.cryptmidterm.util.TLS.*;

public class Main {

    public static void testLFSR() {
        boolean[] seed = {false, false, true, false};
        boolean[] key = {true, false, false, true};
        LFSR lfsr = new LFSR(seed, key);

        for (int i = 0; i < 8; i++) System.out.println(lfsr.extract());
    }

    public static void testRSA() {
        RSA rsa1 = new RSA(17, 11);
        rsa1.printFields();
        RSA rsa2 = new RSA(13, 19);
        rsa2.printFields();
        System.out.println(rsa1.encryptWithPriv(88));
        System.out.println(rsa2.decryptWithPub(rsa1.encryptWithPriv(88), rsa1.getN(), rsa1.getPublicKey()));
        System.out.println(rsa2.decryptWithPriv(rsa1.encryptWithPub(88, rsa2.getN(), rsa2.getPublicKey())));
    }

    public static void testEG() {
        ElGamal elGamal = new ElGamal(11, 6, 23);
        System.out.println(elGamal.encrypt(10, 9, 23, 4).getFirst());
        System.out.println(elGamal.encrypt(10, 9, 23, 4).getSecond());
        System.out.println(elGamal.decrypt(new Tuple<Integer, Integer>(13, 14)));
    }

    public static void testDH() {
        DiffieHellman diffieHellman1 = new DiffieHellman(11, 23);
        DiffieHellman diffieHellman2 = new DiffieHellman(11, 23);
        diffieHellman1.receive(diffieHellman2.send());
        diffieHellman2.receive(diffieHellman1.send());
        diffieHellman1.printFields();
        diffieHellman2.printFields();
        System.out.println(diffieHellman1.getKey() == diffieHellman2.getKey());
    }

    public static void testRO() {
        RandomOracle randomOracle = new RandomOracle(128);
        System.out.println(randomOracle.hash(0));
        System.out.println(randomOracle.hash(128));
        System.out.println(randomOracle.hash(130));
        System.out.println(randomOracle.hash(0));
        System.out.println(randomOracle.hash(128));
        System.out.println(randomOracle.hash(130));
    }

    public static void testTLS() {
        Client client = new
                Client(null,
                null,
                null,
                null,
                null);
        Server server = new
                Server(null,
                null,
                null,
                null);

        ClientHello cHello = client.sayHello();
        ServerHello sHello = server.sayHelloBack(cHello);
        ClientKeyExchange cExchange = client.verifyResponse(sHello);
        server.verifyResponse(cExchange);
    }

    public static void main(String[] args) {
        testTLS();
    }
}
