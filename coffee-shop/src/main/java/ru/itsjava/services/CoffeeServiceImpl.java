package ru.itsjava.services;

import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.NoCoffeeForThatPriceException;

import java.util.ArrayList;
import java.util.Arrays;


public class CoffeeServiceImpl implements CoffeeService {
    private final ArrayList<Coffee> coffeeMenu;

    // filling coffee menu list in constructor:
    public CoffeeServiceImpl() {
        coffeeMenu = new ArrayList<>(Arrays.asList(
                new Coffee("americano", 100.00),
                new Coffee("cappuccino", 150.00),
                new Coffee("espresso", 200.00),
                new Coffee("macchiato", 250.00),
                new Coffee("latte", 300.00)));
    }

    @Override
    public Coffee getCoffeeByPrice(double price) {
        for (Coffee elemCoffee : coffeeMenu) {
            if (elemCoffee.getPrice() == price) {
                return elemCoffee;
            }
        }
        throw new NoCoffeeForThatPriceException();
    }

    @Override
    public void printCoffeeMenu() {
        System.out.println("Here's our coffee menu:");
        System.out.println("_______________________");
        int count = 1;
        for (Coffee elemCoffee : coffeeMenu) {
            System.out.println(count + ". " + elemCoffee.getType() + " -- $" + elemCoffee.getPrice());
            count++;
        }
        System.out.println("_______________________");
    }
}
