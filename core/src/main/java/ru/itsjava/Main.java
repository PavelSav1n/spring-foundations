package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // CPXAC -- shortcode of ClassPathXmlApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        // Создаём объект через рефлексию по названию класса. Передаём в спринг название и он вызывает конструктор и создаёт объект.
        Animal pig = context.getBean("pig", Animal.class);
        pig.say();
    }
}
