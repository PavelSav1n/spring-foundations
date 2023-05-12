package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.itsjava.services.CoffeeHouseImpl;

@EnableAspectJAutoProxy
@ComponentScan("ru.itsjava")
public class Application {
    public static void main(String[] args) {
        //ACAC
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        context.getBean("coffeeHouseImpl", CoffeeHouseImpl.class).customerCoffeeOrder();

    }
}
