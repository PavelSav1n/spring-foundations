package ru.itsjava.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
@Component
public class LoggingAspect {
    File logFileLastOrder = new File("coffee-shop-annotation-based-config/src/main/resources/last-order.txt");
    File logFileLastException = new File("coffee-shop-annotation-based-config/src/main/resources/last-wrong-price.txt");

    @After("execution(* ru.itsjava.services.CoffeeService.getCoffeeByPrice(..))")
    public void logLastCustomerOrder(JoinPoint joinPoint) {
        try (PrintWriter printWriter = new PrintWriter(logFileLastOrder)) {
            printWriter.println("Last customer ordered a coffee for a " + joinPoint.getArgs()[0].toString() + "$");
        } catch (FileNotFoundException e) {
            System.out.println("Specify correct file name");
        }
    }

    //TODO: Отлов exception так и не получился. Код ниже ломает создание бина coffeeHouseImpl и вылетает с BeanNotOfRequiredTypeException
    //TODO: Pointcut указывающий на вызов конструктора тоже не получился. Нужен ман по выражением Pointcut'ов
//    @Pointcut("within(ru.itsjava..*) && execution(* *(..))")
//    public void matchAllMyMethods() {}
//
//    @AfterThrowing(value = "matchAllMyMethods()", throwing = "exception")
//    public void logLastExceptionNoCoffeeForThatPrice(JoinPoint joinPoint) {
//        try (PrintWriter printWriter = new PrintWriter(logFileLastException)) {
//            System.out.println("ERROR");
//            printWriter.println("Last customer wanted a coffee for a " + joinPoint.getArgs()[0].toString() + "$");
//        } catch (FileNotFoundException e) {
//            System.out.println("logLastExceptionNoCoffeeForThatPrice");
//        }
//    }

}
