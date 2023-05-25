package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerServiceImpl")
public class ProgrammerServiceImplTest {

    // В классе ProgrammerServiceImpl используются две зависимости:
    //      - private final NotebookService notebookService;
    //      - private final IOService ioService;
    // Поэтому прописываем два бина в конфигурации:

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

        programmerService.hiToNewProgrammer();

    }

}
