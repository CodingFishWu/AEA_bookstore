package serviceImpl;

import dao.OrderDao;
import model.Order;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;

/**
 * Created by fish on 4/17/16.
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:/queue/OrderQueue")
        },
        mappedName="OrderQueue")
public class OrderMessageBean implements MessageListener {
    @EJB
    private OrderDao orderDao;

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage objectMessage = (ObjectMessage)msg;
            Order order = (Order)objectMessage.getObject();
            orderDao.add(order);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}