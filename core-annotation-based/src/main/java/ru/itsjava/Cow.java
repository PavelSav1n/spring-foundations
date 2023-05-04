package ru.itsjava;

import org.springframework.stereotype.Component;

@Component
public class Cow implements Animal{

    @Override
    public void say() {
        System.out.println("Moo-Moo!");
    }
}
