package com.investlee.api.user;

public class AlreadyAgreedException extends RuntimeException {

    public AlreadyAgreedException(String message) {
        super(message);
    }
}