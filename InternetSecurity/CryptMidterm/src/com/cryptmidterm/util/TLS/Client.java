package com.cryptmidterm.util.TLS;

import java.util.Random;

public class Client {
    private ProtocolVersion protocolVersion;
    private Random random;
    private CipherSuite cipherSuites;
    private CompressionMethod compressionMethods;
    private SignedCertificate signedCertificate;

    public Client(ProtocolVersion protocolVersion,
                  Random random,
                  CipherSuite cipherSuites,
                  CompressionMethod compressionMethods,
                  SignedCertificate signedCertificate) {
        this.protocolVersion = protocolVersion;
        this.random = random;
        this.cipherSuites = cipherSuites;
        this.compressionMethods = compressionMethods;
        this.signedCertificate = signedCertificate;
    }

    public ClientHello sayHello() {
        System.out.println("Client: Hello!");
        return new
                ClientHello(protocolVersion,
                random,
                cipherSuites,
                compressionMethods);
    }

    public ClientKeyExchange verifyResponse(ServerHello serverHello) {
        System.out.println("Client: Here are my certificate and our secret!");
        switchKey();
        return new ClientKeyExchange(signedCertificate, null);
    }

    public void switchKey() {
        System.out.println("Client: I switched key!");
        return;
    }
}
