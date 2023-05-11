package ru.itsjava.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.*;

@Aspect
@Component
public class LoggingAspect {

    @After("execution(* ru.itsjava.services.CoffeeServiceImpl.printCoffeeMenu(..))")
    public void logAfter(JoinPoint joinPoint) throws IOException {
        System.out.println("joinPoint.getTarget().getClass().getName() = " + joinPoint.getTarget().getClass().getName());
//        File file = new File("coffee-shop-annotation-based-config/src/main/resources/Log.log");
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(file));
//        writer.write("Hello");
    }

}
