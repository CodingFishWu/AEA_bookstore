package webservice;

import dao.BookDao;
import daoImpl.BookDaoImpl;
import model.Book;

import javax.jws.WebService;

/**
 * Created by fish on 5/18/16.
 */
@WebService
public class BookWebService {
    private BookDao bookDao = new BookDaoImpl();

    public Book[] query() {
        return bookDao.list();
    }
}
