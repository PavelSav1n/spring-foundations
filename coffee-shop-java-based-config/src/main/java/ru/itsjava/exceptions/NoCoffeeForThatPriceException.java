package ru.itsjava.exceptions;

public class NoCoffeeForThatPriceException extends RuntimeException {
    // constructor
    public NoCoffeeForThatPriceException() {
        super("There is no coffee for that price in menu!");
    }
}
