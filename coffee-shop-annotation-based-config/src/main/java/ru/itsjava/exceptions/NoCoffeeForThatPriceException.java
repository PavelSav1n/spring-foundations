package ru.itsjava.exceptions;

import org.springframework.stereotype.Component;
import ru.itsjava.annotations.NoCoffee;

@Component
@NoCoffee(message = "TYPE") // пытался отловить в выражении Pointcut этот класс по аннотации
public class NoCoffeeForThatPriceException extends RuntimeException {
    public NoCoffeeForThatPriceException() {
        super("There is no coffee for that price in menu!");
    }
}
