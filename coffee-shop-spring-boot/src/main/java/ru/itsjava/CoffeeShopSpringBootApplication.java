package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.services.CoffeeHouseImpl;

@SpringBootApplication
public class CoffeeShopSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CoffeeShopSpringBootApplication.class, args);

        context.getBean("coffeeHouseImpl", CoffeeHouseImpl.class).customerCoffeeOrder();
    }

}
