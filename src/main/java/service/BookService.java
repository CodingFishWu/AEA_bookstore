package service;

import model.Book;

import javax.ejb.Remote;

/**
 * Created by fish on 3/27/16.
 */
@Remote
public interface BookService {
    Book[] getList();
    Book[] getList(String name, String author);
    Book get(String id);
    boolean add(Book book);
    boolean update(String id, Book book);
    boolean delete(String id);
}
