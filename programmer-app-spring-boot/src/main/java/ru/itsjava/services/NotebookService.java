package ru.itsjava.services;

import ru.itsjava.domain.Notebook;

public interface NotebookService {
    Notebook getNotebook(); // We are using factory pattern, where some services would create domains for us via similar methods.
}
