package com.cryptmidterm.util.TLS;

import java.util.Random;

public class Server {
    private ProtocolVersion protocolVersion;
    private Random random;
    private CipherSuite cipherSuites;
    private SignedCertificate signedCertificate;

    public Server(ProtocolVersion protocolVersion,
                  Random random,
                  CipherSuite cipherSuites,
                  SignedCertificate signedCertificate) {
        this.protocolVersion = protocolVersion;
        this.random = random;
        this.cipherSuites = cipherSuites;
        this.signedCertificate = signedCertificate;
    }

    public ServerHello sayHelloBack(ClientHello clientHello) {
        System.out.println("Server: Hello!");
        return new ServerHello(null, random, null, null, signedCertificate);
    }

    public void verifyResponse(ClientKeyExchange clientKeyExchange) {
        System.out.println("Server: I got your certificate and keys!");
        switchKey();
        return;
    }

    public void switchKey() {
        System.out.println("Server: I switched key!");
        return;
    }
}
