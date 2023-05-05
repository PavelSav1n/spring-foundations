package ru.itsjava.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class IOServiceImpl implements IOService {
    private final BufferedReader bufferedReader;

    // Здесь автоматом проставляется @Autowired. Чтобы подставился нужный параметр InputStream используем @Value()
    public IOServiceImpl(@Value("#{T(java.lang.System).in}") InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public String input() {
        return bufferedReader.readLine();
    }
}
