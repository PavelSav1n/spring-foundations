package ru.itsjava;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope("prototype") // чтобы проинициализировать каждому фермеру по поросёнку
@Component
public class Piglet implements Animal {

    public Piglet(){
        System.out.println("Piglet constructor");
    }

    @Override
    public void say() {
        System.out.println("Honk-honk!");
    }
}
