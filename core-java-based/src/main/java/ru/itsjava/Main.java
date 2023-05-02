package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava.configuration")
public class Main {
    public static void main(String[] args) {

        // Java Based Configuration of Spring
        // ACAC -- shortcode of AnnotationConfigApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // Создаём объект через рефлексию по названию класса. Передаём в спринг название класса и он вызывает конструктор и создаёт объект.
        KindFarmer farmer = context.getBean("farmer", KindFarmer.class);
        farmer.hiToAnimal();
    }
}
