package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import ru.itsjava.exceptions.NoCoffeeForThatPriceException;

@RequiredArgsConstructor
public class CoffeeHouseImpl implements CoffeeHouse {
    private final CoffeeService coffeeService;
    private final IOService ioService;

    @Override
    public void customerCoffeeOrder() {

        System.out.println("Hello customer!");
        coffeeService.printCoffeeMenu();
        while (true) {
            try {
                System.out.println("What price coffee do you prefer?");
                String coffee = ioService.input();
                System.out.println("Here's your \"" + coffeeService.getCoffeeByPrice(Double.parseDouble(coffee)).getType() + "\"");
                break;
            } catch (NoCoffeeForThatPriceException | NumberFormatException e) {
                System.out.println("There is no coffee for that price in menu! Try another one!");
            }
        }
    }
}
