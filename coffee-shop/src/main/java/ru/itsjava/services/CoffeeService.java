package ru.itsjava.services;

import ru.itsjava.domain.Coffee;

public interface CoffeeService {
    Coffee getCoffeeByPrice(double price);

    void printCoffeeMenu();
}
