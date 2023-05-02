package ru.itsjava.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import ru.itsjava.KindFarmer;
import ru.itsjava.Animal;

// Тут не нужны аннотации о том, что это конфигурационный класс. Сюда указывает @Import из явно указанной конфигурации.
public class MyOtherConfig {

    @Bean
    // чтобы указать, какая из реализаций Animal будет использована в конструкторе, используем аннотацию @Qualifier
    public KindFarmer farmer(@Qualifier("cow") Animal animal){
        return new KindFarmer(animal);
    }
}
