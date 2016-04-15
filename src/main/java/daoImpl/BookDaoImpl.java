package daoImpl;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.UpdateResult;
import dao.BookDao;
import model.Book;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fish on 3/25/16.
 */

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class BookDaoImpl implements BookDao {
    @Override
    public Book[] list() {
        final ArrayList<Book> books = new ArrayList<Book>();
        FindIterable<Document> iterable = getColl().find(Filters.eq("isDeleted", false));
        iterable.forEach(new Block<Document>() {

            @Override
            public void apply(Document document) {
                books.add(documentToBook(document));
            }
        });
        return books.toArray(new Book[0]);
    }

    @Override
    public Book[] list(String name, String author) {
        final ArrayList<Book> books = new ArrayList<Book>();
        FindIterable<Document> iterable = getColl().find(
                Filters.and(Filters.regex("name", name),
                        Filters.regex("author", author),
                        Filters.eq("isDeleted", false)
                ));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                books.add(documentToBook(document));
            }
        });
        return books.toArray(new Book[0]);
    }

    @Override
    public Book get(String id) {
        FindIterable<Document> iterable = getColl().find(
                Filters.and(Filters.eq("id", id), Filters.eq("isDeleted", false)));
        Document document = iterable.first();
        if (document == null) {
            return null;
        }


        return documentToBook(document);
    }

    @Override
    public Boolean update(String id, Book book) {
        Document newBook = bookToDocument(book);
        newBook.append("isDeleted", false);
        newBook.append("createdTime", new Date());
        UpdateResult result = getColl().replaceOne(new Document("id", id).append("isDeleted", false),
                newBook);

        if (result.getModifiedCount() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean add(Book book) {
        Document document = bookToDocument(book);
        document.append("createdTime", new Date());
        document.append("isDeleted", false);
        try {
            getColl().insertOne(document);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(String id) {
        UpdateResult result = getColl().updateOne(new Document("id", id).append("isDeleted", false),
                new Document("$set", new Document("isDeleted", true)));
        if (result.getModifiedCount() == 0) {
            return false;
        }

        return true;
    }

    private MongoCollection<Document> getColl() {
        return ConnectDB.getDB().getCollection("book");
    }

    private Book documentToBook(Document document) {
        Book result = new Book();
        result.setId(document.getString("id"));
        result.setName(document.getString("name"));
        result.setAuthor(document.getString("author"));
        result.setPrice(document.getDouble("price"));
        result.setStock(document.getInteger("stock"));
        result.setCategory(document.getString("category"));
        return result;
    }

    private Document bookToDocument(Book book) {
        Document document = new Document();
        document.append("id", book.getId());
        document.append("name", book.getName());
        document.append("author", book.getAuthor());
        document.append("price", book.getPrice());
        document.append("stock", book.getStock());
        document.append("category", book.getCategory());
        return document;
    }
}
