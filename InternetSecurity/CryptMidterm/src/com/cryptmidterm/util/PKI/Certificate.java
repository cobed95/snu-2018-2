package com.cryptmidterm.util.PKI;

public class Certificate {
    public SerialNumber serialNumber;
    public String subject;
    public Algorithm signatureAlgorithm;
    public String issuer;
    public Date validFrom;
    public Date validTo;
    public String keyUsage;
    public Key publicKey;

    public Certificate(SerialNumber serialNumber,
                       String subject,
                       Algorithm signatureAlgorithm,
                       String issuer,
                       Date validFrom,
                       Date validTo,
                       String keyUsage,
                       Key publicKey) {
        this.serialNumber = serialNumber;
        this.subject = subject;
        this.signatureAlgorithm = signatureAlgorithm;
        this.issuer = issuer;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.keyUsage = keyUsage;
        this.publicKey = publicKey;
    }
}
