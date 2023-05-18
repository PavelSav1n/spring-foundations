package ru.itsjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Возможно использование типа: @Scope("BeanDefinition.SCOPE_PROTOTYPE") или @Scope("prototype")
//@Scope("singleton") // Для вызова синглтон бина данного класса. Создание бина происходит при инициализации контекста
@Scope(BeanDefinition.SCOPE_PROTOTYPE) // Для вызова уникальных бинов. Создание бина происходит в момент вызова бина, а не при инициализации контекста
@Component("farmer")
public class KindFarmer {
    private Animal animal;


    public KindFarmer() {
        System.out.println("KindFarmer constructor");
    }

    public void hiToAnimal(){
        System.out.println("Привет моя любимая");
        animal.say();
    }

    @Autowired // Внедрение зависимости. Будет выполнено после вызова конструктора KindFarmer
    public void setAnimal(Animal animal){
        System.out.println("Setter");
        this.animal = animal;
    }

    @PostConstruct // часть package javax.annotation -- выполнение каких-то методов после вызова конструктора и сеттеров.
    public void PostConstruct(){
        System.out.println("PostConstruct");
    }

    @PreDestroy // часть package javax.annotation -- выполнение каких-то методов перед удалением бина.
    public void PreDestroy(){
        System.out.println("PreDestroy");
    }
}