package ru.itsjava.proxy;

public class BookDaoImpl implements BookDao{

    @Override
    public String getBook(long id) {
        return "Harry Potter";
    }
}
