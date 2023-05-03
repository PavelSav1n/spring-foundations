package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itsjava.services.CoffeeHouseImpl;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        context.getBean("CoffeeHouse", CoffeeHouseImpl.class).customerCoffeeOrder();

    }
}
