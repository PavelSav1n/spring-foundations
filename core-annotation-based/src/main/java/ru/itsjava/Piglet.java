package ru.itsjava;

import org.springframework.stereotype.Component;

@Component
public class Piglet implements Animal {
    @Override
    public void say() {
        System.out.println("Honk-honk!");
    }
}
