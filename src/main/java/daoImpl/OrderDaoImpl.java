package daoImpl;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dao.OrderDao;
import model.Item;
import model.Order;
import org.bson.Document;

import javax.ejb.Stateless;
import java.util.*;

/**
 * Created by fish on 4/8/16.
 */
@Stateless
public class OrderDaoImpl implements OrderDao{

    @Override
    public Boolean add(Order order) {
        Document document = new Document();
        document.append("username", order.getUsername());
        document.append("time", order.getTime());
        document.append("id", order.getId());
        document.append("status", order.getStatus());
        document.append("isDeleted", false);
        ArrayList<Document> documents= new ArrayList<Document>();
        List<Item> items = order.getItems();
        for (Item item : items) {
            documents.add(new Document().append("bookId", item.getBookId()).append("number", item.getNumber()));
        }
        document.append("items", documents);
        getColl().insertOne(document);
        return true;
    }

    @Override
    public Order[] list(String username) {
        FindIterable<Document> iterable = getColl().find(Filters.and(
                Filters.eq("username", username),
                Filters.eq("isDeleted", false)));
        final ArrayList<Order> orders = new ArrayList<Order>();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                orders.add(documentToOrder(document));
            }
        });

        return orders.toArray(new Order[0]);
    }

    private MongoCollection<Document> getColl() {
        return ConnectDB.getDB().getCollection("order");
    }

    private Order documentToOrder(Document document) {
        Order order = new Order();
        order.setId(document.getString("id"));
        order.setUsername(document.getString("username"));
        order.setTime(document.getDate("time"));
        order.setStatus(document.getString("status"));
        // get items
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList documents = document.get("items", ArrayList.class);
        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            Document documentTemp = (Document)iterator.next();
            Item item = new Item(documentTemp.getString("bookId"), documentTemp.getInteger("number"));
            items.add(item);
        }
        order.setItems(items);

        return order;
    }
}
