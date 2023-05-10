package ru.itsjava.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
//               aspect
public class LoggingAspect {

    // совет @Before -- отработает до вызова объекта из указанного pointcut
    //advice  pointcut          on what will be called (parameters)
    @Before("execution(* ru.itsjava.services.FilmService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Log Before " + joinPoint.getTarget().getClass().getName());
    }

    // совет @After -- отработает после вызова объекта из указанного pointcut
    @After("execution(* ru.itsjava.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Log After " + joinPoint.getTarget().getClass().getName());
    }

    // совет @Around -- порядок вызовов sout зависит от их расположения относительно вызова метода proceed()
    // Находится дальше от вызываемого объекта
    @Around("execution(* ru.itsjava.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Отработает до совета @Before, потом @Before, потом будет вызван наш pointcut объект
        System.out.println("Log Around Before " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
        // Необходимо вызвать proceed() на данном ProceedingJoinPoint, а затем вернуть значение, чтобы не потерять изначальный объект (без этого будет null)
        Object result = proceedingJoinPoint.proceed();
        // Отработает после совета @After
        System.out.println("Log Around After " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
        return result;
    }

}
