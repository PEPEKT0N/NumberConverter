package org.top.springpractice.converter;

public class UnsupportedNumberException extends RuntimeException {
    public UnsupportedNumberException(String code) {
        super("number system '" + code + "' is unsupported");
    }
}
