package org.top.springpractice.api;

public class EmptyRequestDataException extends RuntimeException {
    public EmptyRequestDataException(String name) {
        super("'" + name + "' is null or empty");
    }
}
