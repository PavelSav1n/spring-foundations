package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOServiceImpl implements IOService {
    private final BufferedReader bufferedReader;

    // Вызываем конструктор из context.xml и передаём в качестве InputStream (java.lang.System).in
    public IOServiceImpl(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public String input() {
        return bufferedReader.readLine();
    }
}
