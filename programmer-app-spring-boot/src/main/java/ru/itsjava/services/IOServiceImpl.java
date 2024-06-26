package ru.itsjava.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service("ioService") // Аннотация @Service, потому что это сервис. Наследуется от @Component.
public class IOServiceImpl implements IOService {
    private final BufferedReader bufferedReader;

    // Значения из app.properties можно пихать прямо в конструктор через @Value
    // Сейчас мы используем application.yaml, у Spring есть для этого библиотека
    public IOServiceImpl(@Value("#{T(java.lang.System).in}") InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println("This is IOServiceImpl constructor"); // for test
    }

    @SneakyThrows
    @Override
    public String input() {
        return bufferedReader.readLine();
    }
}
