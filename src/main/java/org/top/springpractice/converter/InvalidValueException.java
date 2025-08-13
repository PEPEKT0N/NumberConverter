package org.top.springpractice.converter;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String value) {
        super("value" + value + "is invalid");
    }
}
