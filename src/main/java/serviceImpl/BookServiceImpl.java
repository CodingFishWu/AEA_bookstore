package serviceImpl;

import dao.BookDao;
import model.Book;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import servlet.Tools;

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

    Jedis jedis = new Jedis("localhost");

    @Override
    public Book[] getList() {
        Book[] books = bookDao.list();
        cacheToRedis(books);
        return books;
    }

    @Override
    public Book[] getList(String name, String author) {
        Book[] books = bookDao.list(name, author);
        cacheToRedis(books);
        return books;
    }

    @Override
    public Book get(String id) {
        String s = jedis.get(id);
        if (s != null) {
            Book book = Tools.jsonObjectToBook(new JSONObject(s));
            return book;
        }
        else {
            return bookDao.get(id);
        }
    }

    @Override
    public boolean add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public boolean update(String id, Book book) {
        if (bookDao.update(id, book)) {
            jedis.set(id, Tools.bookToJSONObject(book).toString());
            return true;
        }
        else {
            return false;
        }
//        return bookDao.update(id, book);
    }

    @Override
    public boolean delete(String id) {
        if (bookDao.delete(id)) {
            jedis.del(id);
            return true;
        }
        else {
            return false;
        }
//        return bookDao.delete(id);
    }

    private void cacheToRedis(Book[] books) {
//        Jedis jedis = new Jedis("localhost");
        for (Book book : books) {
            String key = book.getId();
            JSONObject jsonObject = Tools.bookToJSONObject(book);
            jedis.set(key, jsonObject.toString());
        }
    }
}
