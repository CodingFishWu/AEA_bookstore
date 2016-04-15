package dao;

import model.Book;

import javax.ejb.Local;

/**
 * Created by fish on 3/25/16.
 */
@Local
public interface BookDao {
    Book[] list();
    Book[] list(String name, String author);

    Book get(String id);

    Boolean update(String id, Book other);

    Boolean add(Book book);

    Boolean delete(String id);
}
