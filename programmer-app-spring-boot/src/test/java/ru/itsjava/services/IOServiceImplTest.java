package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Class IOServiceImpl")
public class IOServiceImplTest {

    @Configuration
    static class MyConfiguration{

        public static final String CONTROL_INPUT_STRING = "Hello"; // строка, которую будем вводить в конструктор через byteArrayInputStream
        private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(CONTROL_INPUT_STRING.getBytes()); // input stream для конструктора

        @Bean // конфигурируем бин, на котором будем вызывать проверяемый метод input()
        public IOService ioService(){
            System.out.println("Конструктор из теста ioService ");
            return new IOServiceImpl(byteArrayInputStream);
        }
    }

    @Autowired //TODO: Что делается тут? Идётся в контекст и присваивается переменной бин с таким же именем?
    private IOService ioService;

    @DisplayName("корректно работает метод input()")
    @Test
    public void shouldHaveCorrectMethodInput(){
        // TODO: не ясно, как тут вызывается на ioService.input(), потому что нигде мы не получаем бин типа .getBean
        assertEquals(MyConfiguration.CONTROL_INPUT_STRING, ioService.input());
    }
}
