package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.itsjava.services.CoffeeHouse;
import ru.itsjava.services.CoffeeHouseImpl;

@ComponentScan("ru.itsjava.configuration")
public class Application {
    public static void main(String[] args) {
        //ACAC
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        CoffeeHouse coffeeHouse = context.getBean("coffeeHouse", CoffeeHouseImpl.class);
        coffeeHouse.customerCoffeeOrder();
    }
}
