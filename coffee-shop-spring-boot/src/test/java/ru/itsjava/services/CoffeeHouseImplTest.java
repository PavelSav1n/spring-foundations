package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Class CoffeeHouseImpl")
public class CoffeeHouseImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public CoffeeService coffeeService() {
            // Можно просто вернуть
            return new CoffeeServiceImpl();
            // Использовать тут заглушку (stub) Mockito не получится, потому что из CoffeeServiceImpl вызывается два метода:
            //  - getCoffeeByPrice(), который возвращает Coffee и с этим всё ок, тут можно отловить через when и вернуть новый экземпляр Coffee
            //  - void printCoffeeMenu(), который ничего не возвращает, а просто выводит список кофе в консоль. Не понятно, как это отлавливать в Mockito

        }

        @Bean
        public IOService ioService() {
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("100");
            return mockIOService;
        }

        @Bean
        public CoffeeHouse coffeeHouse(CoffeeService coffeeService, IOService ioService) {
            return new CoffeeHouseImpl(coffeeService, ioService);
        }
    }

    @Autowired
    private CoffeeHouse coffeeHouse;

    @Test
    @DisplayName(" Should have correct method customerCoffeeOrder()")
    public void shouldHaveCorrectMethodCustomerCoffeeOrder() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        coffeeHouse.customerCoffeeOrder();

        assertEquals("Hello customer!\r\n" +
                "Here's our coffee menu:\r\n" +
                "_______________________\r\n" +
                "1. americano -- $100.0\r\n" +
                "2. cappuccino -- $150.0\r\n" +
                "3. espresso -- $200.0\r\n" +
                "4. macchiato -- $250.0\r\n" +
                "5. latte -- $300.0\r\n" +
                "_______________________\r\n" +
                "What price coffee do you prefer?\r\n" +
                "Here's your \"americano\"\r\n", out.toString());
    }
}
