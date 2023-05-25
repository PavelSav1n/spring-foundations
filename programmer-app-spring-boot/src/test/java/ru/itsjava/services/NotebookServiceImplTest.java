package ru.itsjava.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

// Как лучше всего конфигрировать бины для тестов, чтобы тестились только необходимые бины?
//1. Аннотация @ComponentScan("ru.itsjava.services") // Будут созданы все бины из services
//2. Так же можно создать отдельный конфигурационный класс @SpringBootConfiguration и в нём сконфигурировать бины, но тогда у нас на каждый тест будет свой отдельный класс конфигурации
//3. Можно создать в этом же классе статический класс конфигурации, где прописываем нужные бины. Это мы и сделаем.
@SpringBootTest
@DisplayName("Класс NotebookServiceImpl")
public class NotebookServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean // Обязательно пишем аннотацию бина!
        public NotebookService notebookService() {
            // передаём параметры тут:
            return new NotebookServiceImpl("Apple", "air", 2023);
        }
    }

    @Autowired
    private NotebookService notebookService;


    @DisplayName("Метод getNotebook() должен отдавать корректный ноутбук ")
    @Test
    public void shouldHaveCorrectMethodGetNotebook() {
        Notebook notebook = notebookService.getNotebook();
        Assertions.assertEquals(notebook.getFirm(), "Apple");
    }

}
