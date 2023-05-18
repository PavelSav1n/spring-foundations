package ru.itsjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itsjava.exceptions.NoCoffeeForThatPriceException;

@Service
//@RequiredArgsConstructor
public class CoffeeHouseImpl implements CoffeeHouse {
    private final CoffeeService coffeeService;
    private final IOService ioService;

    @Autowired
    public CoffeeHouseImpl(CoffeeService coffeeService, IOService ioService) {
        this.coffeeService = coffeeService;
        this.ioService = ioService;
    }

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
            } catch (NumberFormatException | NoCoffeeForThatPriceException e) {
                System.out.println("There is no coffee for that price in menu! Try another one!");
            }
        }
    }
}
