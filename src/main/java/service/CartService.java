package service;

import model.Item;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by fish on 3/27/16.
 */
@Remote
public interface CartService {
    boolean put(String bookId);

    boolean delete(String bookId);

    ArrayList<Item> get();

    boolean toOrder(String username);
}
