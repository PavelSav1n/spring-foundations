package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava")
public class Main {
    public static void main(String[] args) {

        // Annotation Based Configuration of Spring
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // Создаём объект через рефлексию по названию класса. Передаём id класса и сам класс и получаем бин -- POJO (Plain old Java object)
        KindFarmer farmer = context.getBean("farmer", KindFarmer.class);
        farmer.hiToAnimal();
    }
}
