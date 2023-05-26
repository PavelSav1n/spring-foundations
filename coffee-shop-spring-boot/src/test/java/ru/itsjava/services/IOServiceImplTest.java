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
    static class MyConfiguration {

        // Создаём input stream с заданной строкой:
        public static final String TEST_INPUT_STRING = "Test Input";
        private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TEST_INPUT_STRING.getBytes());

        @Bean
        public IOService ioService() {
            return new IOServiceImpl(byteArrayInputStream);
        }
    }

    @Autowired
    private IOService ioService;

    @Test
    @DisplayName("Should have correct input() method")
    public void shouldHaveCorrectMethodInput(){
        assertEquals(MyConfiguration.TEST_INPUT_STRING, ioService.input());
    }

}
