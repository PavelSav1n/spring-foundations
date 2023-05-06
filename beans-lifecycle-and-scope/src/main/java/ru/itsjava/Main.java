package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava")
public class Main {
    public static void main(String[] args) {

        // Annotation Based Configuration of Spring
        // Тип ApplicationContext меняем на AnnotationConfigApplicationContext для вызова метода close()
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // При использовании singleton (по умолчанию все бины) даже если закомментировать блок ниже, все бины создадутся.
        KindFarmer farmer1 = context.getBean(KindFarmer.class);
        KindFarmer farmer2 = context.getBean(KindFarmer.class);

        System.out.println(farmer1);
        System.out.println(farmer2);
        System.out.println("farmer1 == farmer2 = " + (farmer1 == farmer2));

        context.close();

    }
}
