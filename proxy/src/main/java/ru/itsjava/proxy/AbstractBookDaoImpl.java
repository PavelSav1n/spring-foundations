package ru.itsjava.proxy;

public class AbstractBookDaoImpl extends AbstractBookDao{
    @Override
    public String getBook(long id) {
        return "Lord of the Rings";
    }
}
