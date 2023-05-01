package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itsjava.services.ProgrammerService;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        ProgrammerService programmerService = context.getBean("programmerService", ProgrammerService.class);
        programmerService.hiToNewProgrammer();
    }
}
