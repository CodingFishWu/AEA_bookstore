package webservice;

import model.Book;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created by fish on 5/18/16.
 */
public class BookWsClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        BookWebService service = new BookWebService();
        Book[] books = service.query();
        for (Book book : books) {
            System.out.println(book.getName());
        }
    }
}
