package ru.itsjava.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Aspect
@Component
public class LoggingAspect {
    File logFileLastOrder = new File("coffee-shop-spring-boot/src/main/resources/last-order.txt");
    File logFileLastException = new File("coffee-shop-spring-boot/src/main/resources/last-wrong-price.txt");

    @After("execution(* ru.itsjava.services.CoffeeService.getCoffeeByPrice(..))")
    public void logLastCustomerOrder(JoinPoint joinPoint) {
        try (PrintWriter printWriter = new PrintWriter(logFileLastOrder)) {
            printWriter.println("Last customer ordered a coffee for a " + joinPoint.getArgs()[0].toString() + "$");
        } catch (FileNotFoundException e) {
            System.out.println("Specify correct file name");
        }
    }

    // Закоменченный ниже pointcut не отловился. Вылетала ошибка на создание бина coffeeHouseImpl -- BeanNotOfRequiredTypeException (был sun...$Proxy7)
    // Видимо execution (* *(..)) не отлавливал нужный класс.
//    @Pointcut("within(ru.itsjava..*) && execution(* *(..))")
    @Pointcut("execution(* ru.itsjava.services.CoffeeService.getCoffeeByPrice(..))")
    public void matchAllMyMethods() {}

    @AfterThrowing(value = "matchAllMyMethods()", throwing = "exception")
    public void logLastExceptionNoCoffeeForThatPrice(JoinPoint joinPoint) {
        try (PrintWriter printWriter = new PrintWriter(logFileLastException)) {
            System.out.println("ERROR");
            printWriter.println("Last customer wanted a coffee for a " + joinPoint.getArgs()[0].toString() + "$");
        } catch (FileNotFoundException e) {
            System.out.println("logLastExceptionNoCoffeeForThatPrice");
        }
    }

}
