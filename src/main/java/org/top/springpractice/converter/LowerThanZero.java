package org.top.springpractice.converter;

public class LowerThanZero extends RuntimeException {
    public LowerThanZero(String value) {
        super(value + " - unsupported. Value should be greater than zero");
    }
}
