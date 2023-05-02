package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // CPXAC -- shortcode of ClassPathXmlApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        // IoC = inversion of control (инверсия управления)
        //        Animal piglet = context.getBean("pig", Animal.class);
        //        piglet.say();

        // DI = Dependency Injection (внедрение зависимостей)
        // Создаём объект через рефлексию по названию класса. Передаём в спринг название и он вызывает конструктор и создаёт объект.
        KindFarmer farmer = context.getBean("farmer", KindFarmer.class);
        farmer.hiToAnimal();
    }
}
