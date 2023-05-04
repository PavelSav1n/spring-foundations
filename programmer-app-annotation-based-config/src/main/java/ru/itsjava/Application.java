package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.itsjava.services.ProgrammerService;

@ComponentScan("ru.itsjava.services")
@PropertySource("classpath:app.properties") // путь до файла конфигурации
public class Application {
    public static void main(String[] args) {
        //ACAC
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        ProgrammerService programmerService = context.getBean("programmerService", ProgrammerService.class);
        programmerService.hiToNewProgrammer();
    }
}
