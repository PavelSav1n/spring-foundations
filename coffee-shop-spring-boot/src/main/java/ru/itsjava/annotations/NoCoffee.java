package ru.itsjava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Для отлова класса по аннотации
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoCoffee {
    String message();
}
