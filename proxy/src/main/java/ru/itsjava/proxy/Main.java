package ru.itsjava.proxy;

public class Main {
    public static void main(String[] args) {

        BookDao bookDao = new BookDaoImpl();
        System.out.println("bookDao.getBook(1L) = " + bookDao.getBook(1L));

        BookDao bookDaoProxy = new BookDaoImplProxy();
        System.out.println("bookDao.getBook(1L) = " + bookDaoProxy.getBook(1L));

        AbstractBookDao abstractBookDao = new AbstractBookDaoImpl();
        System.out.println("abstractBookDao.getBook(1L) = " + abstractBookDao.getBook(1L));

        AbstractBookDao abstractBookDaoProxy = new AbstractBookDaoImplProxy();
        System.out.println("abstractBookDaoProxy.getBook(1L) = " + abstractBookDaoProxy.getBook(1L));
    }
}
