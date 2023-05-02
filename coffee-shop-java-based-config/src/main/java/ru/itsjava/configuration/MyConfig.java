package ru.itsjava.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.*;

import java.io.InputStream;

@Configuration
public class MyConfig {

    @Value("#{T(java.lang.System).in}")
    private InputStream inputStream;

    @Bean
    public CoffeeService coffeeService() {
        return new CoffeeServiceImpl();
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(inputStream);
    }

    @Bean
    public CoffeeHouse coffeeHouse(CoffeeService coffeeService, IOService ioService) {
        return new CoffeeHouseImpl(coffeeService, ioService);
    }

}
