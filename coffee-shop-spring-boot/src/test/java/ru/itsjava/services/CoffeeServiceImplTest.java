package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.NoCoffeeForThatPriceException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Class CoffeeServiceImpl")
public class CoffeeServiceImplTest {

    // Задаём ArrayList для проверок:
    private final ArrayList<Coffee> coffeeMenu = new ArrayList<>(Arrays.asList(
            new Coffee("americano", 100.00),
            new Coffee("cappuccino", 150.00),
            new Coffee("espresso", 200.00),
            new Coffee("macchiato", 250.00),
            new Coffee("latte", 300.00)));

    private final Coffee controlWrongCoffee = new Coffee("NotCoffee", 123.00);

    @Configuration
    static class MyConfiguration {

        @Bean
        public CoffeeService coffeeService() {
            return new CoffeeServiceImpl();
        }
    }

    @Autowired
    private CoffeeService coffeeService;

    @Test
    @DisplayName("Should have correct method getCoffeeByPrice()")
    public void shouldHaveCorrectMethodGetCoffeeByPrice() {
        assertEquals(coffeeMenu.get(0), coffeeService.getCoffeeByPrice(coffeeMenu.get(0).getPrice()));
    }

    @Test
    @DisplayName("Should have correct exception message for method getCoffeeByPrice()")
    public void shouldHaveCorrectExceptionGetCoffeeByPrice() {
        Exception exception = assertThrows(NoCoffeeForThatPriceException.class, () -> coffeeService.getCoffeeByPrice(controlWrongCoffee.getPrice()));
        //      assertThrows()   --     Class<T> expectedType ^^^                        executable ^^^
        String expectedMessage = "There is no coffee for that price in menu!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should have correct method printCoffeeMenu()")
    public void shouldHaveCorrectMethodPrintCoffeeMenu(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        coffeeService.printCoffeeMenu();
        // Внимательно с переносами строк: В Windows CRLF (\r\n), в *NIX & macOS LF (\n)
        assertEquals("Here's our coffee menu:\r\n" +
                "_______________________\r\n" +
                "1. americano -- $100.0\r\n" +
                "2. cappuccino -- $150.0\r\n" +
                "3. espresso -- $200.0\r\n" +
                "4. macchiato -- $250.0\r\n" +
                "5. latte -- $300.0\r\n" +
                "_______________________\r\n", out.toString());
    }


}
