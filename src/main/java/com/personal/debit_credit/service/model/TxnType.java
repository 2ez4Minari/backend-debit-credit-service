package com.personal.debit_credit.service.model;

public enum TxnType {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String type;

    TxnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Method to get enum based on string value
    public static TxnType fromString(String type) {
        for (TxnType txnType : TxnType.values()) {
            if (txnType.type.equalsIgnoreCase(type)) {
                return txnType;
            }
        }
        throw new IllegalArgumentException("Unknown transaction type: " + type);
    }
}
