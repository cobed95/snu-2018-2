package com.cryptmidterm.util.TLS;

import java.util.Random;

public class ServerHello {
    public ProtocolVersion highestPV;
    public Random random;
    public CipherSuite highestCS;
    public CompressionMethod highestCM;
    public SignedCertificate signedCertificate;

    public ServerHello(ProtocolVersion highestPV,
                       Random random,
                       CipherSuite highestCS,
                       CompressionMethod highestCM,
                       SignedCertificate signedCertificate) {
        this.highestPV = highestPV;
        this.random = random;
        this.highestCS = highestCS;
        this.highestCM = highestCM;
        this.signedCertificate = signedCertificate;
        System.out.println("ServerHello Finished.");
    }
}
