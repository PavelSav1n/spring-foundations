package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import ru.itsjava.domain.Notebook;

@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {
    private final String firm;
    private final String model;
    private final int year;

    @Override
    public Notebook getNotebook() {
        return new Notebook(firm, model, year);
    }
}
