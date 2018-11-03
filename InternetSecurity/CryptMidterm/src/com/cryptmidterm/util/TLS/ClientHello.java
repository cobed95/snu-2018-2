package com.cryptmidterm.util.TLS;

import java.util.Random;

public class ClientHello {
    public ProtocolVersion protocolVersion;
    public Random random;
    public SessionID sessionID;
    public CipherSuite cipherSuites;
    public CompressionMethod compressionMethods;

    public ClientHello(ProtocolVersion protocolVersion,
                       Random random,
                       CipherSuite cipherSuites,
                       CompressionMethod compressionMethods) {
        this(protocolVersion,
                random,
                null,
                cipherSuites,
                compressionMethods);
    }

    public ClientHello(ProtocolVersion protocolVersion,
                       Random random,
                       SessionID sessionID,
                       CipherSuite cipherSuites,
                       CompressionMethod compressionMethods) {
        this.protocolVersion = protocolVersion;
        this.random = random;
        this.sessionID = sessionID;
        this.cipherSuites = cipherSuites;
        this.compressionMethods = compressionMethods;
        System.out.println("ClientHello Finished.");
    }
}
