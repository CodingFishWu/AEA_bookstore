package serviceImpl;

import dao.BookDao;
import model.Book;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by fish on 3/25/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class BookServiceImpl implements service.BookService {
    @EJB
    BookDao bookDao;

    @Override
    public Book[] getList() {
        return bookDao.list();
    }

    @Override
    public Book[] getList(String name, String author) {

        return bookDao.list(name, author);
    }

    @Override
    public Book get(String id) {
        return bookDao.get(id);
    }

    @Override
    public boolean add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public boolean update(String id, Book book) {
        return bookDao.update(id, book);
    }

    @Override
    public boolean delete(String id) {
        return bookDao.delete(id);
    }
}
