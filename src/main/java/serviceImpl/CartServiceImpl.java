package serviceImpl;

import dao.OrderDao;
import model.Item;
import model.Order;
import service.CartService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.InitialContext;
import java.util.*;

/**
 * Created by fish on 3/27/16.
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateful
public class CartServiceImpl implements CartService{
    @EJB
    OrderDao orderDao;





    private ArrayList<Item> items = new ArrayList<Item>();
    private int count = 0;

    @Override
    public boolean put(String bookId) {
        for (Item item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setNumber(item.getNumber() + 1);
                return true;
            }
        }
        items.add(new Item(bookId, 1));
        return true;
    }

    @Override
    public boolean delete(String bookId) {
        for (Item item : items) {
            if (item.getBookId().equals(bookId)) {
                items.remove(item);
            }
        }
        return true;
    }

    @Override
    public ArrayList<Item> get() {
        return items;
    }

    @Override
    public boolean toOrder(String name) {
        Order order = new Order();
        order.setUsername(name);
        order.setTime(new Date());
        order.setStatus("unpaid");
        order.setItems(items);
        // get a random id
        Calendar now = Calendar.getInstance();
        Random random = new Random();
        random.setSeed(name.hashCode());
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        String id = String.format("%d%d%d%d%d%d%d", year, month, day, hour, minute, second, random.nextInt()%1000000);
        order.setId(id);

//        orderDao.add(order);

        try {
            InitialContext ctx = new InitialContext();
            Queue dest = (Queue) ctx.lookup("/queue/OrderQueue");
            QueueConnectionFactory factory
                    = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            QueueConnection cnn = factory.createQueueConnection();
            QueueSession session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(dest);
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(order);
            sender.send(objectMessage);
            session.close();
            cnn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}
