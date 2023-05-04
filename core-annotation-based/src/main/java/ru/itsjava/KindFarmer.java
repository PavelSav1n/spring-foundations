package ru.itsjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("farmer")
public class KindFarmer {
    private final Animal animal;

    @Autowired // @Autowired можно не писать. Просто запомнить, что он тут прописывается автоматом и сохраняется в RUNTIME
    public KindFarmer(@Qualifier("piglet") Animal animal) { // Используем @Qualifier, т.к. у нас две реализации Animal
        this.animal = animal;
    }

    public void hiToAnimal(){
        System.out.println("Привет моя любимая");
        animal.say();
    }
}