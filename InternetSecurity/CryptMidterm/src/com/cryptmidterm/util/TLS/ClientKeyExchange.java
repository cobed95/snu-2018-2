package com.cryptmidterm.util.TLS;

public class ClientKeyExchange {
    public SignedCertificate signedCertificate;
    public PremasterSecret premasterSecret;

    public ClientKeyExchange(SignedCertificate signedCertificate,
                             PremasterSecret premasterSecret) {
        this.signedCertificate = signedCertificate;
        this.premasterSecret = premasterSecret;
        System.out.println("ClientKeyExchange Finished.");
    }
}
