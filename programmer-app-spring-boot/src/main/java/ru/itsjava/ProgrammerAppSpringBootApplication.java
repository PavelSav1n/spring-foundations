package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.domain.ConditionalDomain;
import ru.itsjava.services.ConditionalService;
import ru.itsjava.services.ConditionalServiceImpl;
import ru.itsjava.services.ProgrammerService;

@SpringBootApplication
public class ProgrammerAppSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProgrammerAppSpringBootApplication.class, args);

        ProgrammerService programmerService = context.getBean("programmerService", ProgrammerService.class);
        programmerService.hiToNewProgrammer();

        // If bean with specified name exists:
        if (context.containsBean("conditionalServiceImpl")) {
            ConditionalService conditionalService = context.getBean("conditionalServiceImpl", ConditionalService.class);
            System.out.println(conditionalService);
            System.out.println(conditionalService.getConditionalDomain());
        }

    }

}
