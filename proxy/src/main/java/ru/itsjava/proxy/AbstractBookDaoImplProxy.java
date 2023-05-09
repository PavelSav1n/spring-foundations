package ru.itsjava.proxy;

public class AbstractBookDaoImplProxy extends AbstractBookDao{
    @Override
    public String getBook(long id) {
        System.out.println("Hello from abstract class");
        return new AbstractBookDaoImpl().getBook(id);
    }
}
