package ru.itsjava;

public class KindFarmer {
    private final Animal animal;

    public KindFarmer(Animal animal) {
        this.animal = animal;
    }

    public void hiToAnimal(){
        System.out.println("Привет моя любимая");
        animal.say();
    }
}