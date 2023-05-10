package ru.itsjava.services;

import org.springframework.stereotype.Service;
import ru.itsjava.domain.Film;

@Service
public class FilmServiceImpl implements FilmService{

    public FilmServiceImpl() {
        System.out.println("Constructor FilmServiceImpl");
    }

    @Override
    public Film getFilm() {
        System.out.println("getFilm() invocation");
        return new Film("Lord of The Rings");
    }
}
