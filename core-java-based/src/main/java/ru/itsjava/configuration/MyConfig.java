package ru.itsjava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.Animal;
import ru.itsjava.Cow;
import ru.itsjava.Piglet;

@Configuration // аннотация, для @ComponentScan в Main о том, что тут находится конфигурация для спринга
@Import(MyOtherConfig.class) // указание на другие конфигурационные классы
public class MyConfig {

    @Bean
    public Animal piglet(){
        return new Piglet();
    }

    @Bean Animal cow(){
        return new Cow();
    }


}
