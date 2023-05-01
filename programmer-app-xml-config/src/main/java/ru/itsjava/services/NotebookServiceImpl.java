package ru.itsjava.services;

import ru.itsjava.domen.Notebook;

public class NotebookServiceImpl implements NotebookService {
    @Override
    public Notebook getNotebook() {
        return new Notebook("Apple", "MacbookPro", 2022);
    }
}
