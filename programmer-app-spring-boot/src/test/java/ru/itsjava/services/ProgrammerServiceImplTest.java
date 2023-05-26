package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerServiceImpl")
public class ProgrammerServiceImplTest {

    // В классе ProgrammerServiceImpl используются две зависимости:
    //      - private final NotebookService notebookService;
    //      - private final IOService ioService;
    // Поэтому прописываем три бина в конфигурации, два подставных и третий из первых двух:

    @Configuration
    static class MyConfiguration {

        @Bean
        public IOService ioService() {
            // В IOServiceImpl используется System.in и чтобы не проверять ещё и этот стрим и не писать исключения, используем Mockito:
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            // Когда я буду вызывать input и вызывать System.in, просто отдаём другую строку:
            when(mockIOService.input()).thenReturn("Pavel");
            return mockIOService;
        }

        @Bean
        public NotebookService notebookService() {
            // Тоже будем отдавать подставной бин через Mockito:
            NotebookServiceImpl mockNotebookService = Mockito.mock(NotebookServiceImpl.class);
            when(mockNotebookService.getNotebook()).thenReturn(new Notebook("Asus", "SomeModel", 2230));
            return mockNotebookService;
        }

        @Bean
        public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {
            return new ProgrammerServiceImpl(notebookService, ioService);
        }
    }

    @Autowired
    private ProgrammerService programmerService;


    @DisplayName("Должен корректно приветствовать нового программиста")
    @Test
    public void ShouldHaveCorrectSayHiToNewProgrammer() {

        ByteArrayOutputStream out = new ByteArrayOutputStream(); // байтовый стрим
        //System.out можно переопределить через System.setOut:
        System.setOut(new PrintStream(out)); // пишем стрим System.out в новый PrintStream байтовый поток out. То есть весь System.out теперь хранится в байтовом стриме "out"
        programmerService.hiToNewProgrammer();

        assertEquals("Enter your name: \r\n" +
                "Hello, Pavel\r\n" +
                "Your computer: Notebook {Asus SomeModel 2230}\r\n", out.toString());

    }

}
