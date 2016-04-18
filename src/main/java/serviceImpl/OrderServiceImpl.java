package serviceImpl;

import dao.OrderDao;
import model.Order;
import service.OrderService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by fish on 4/9/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class OrderServiceImpl implements OrderService{
    @EJB
    OrderDao orderDao;

    @Override
    public Order[] list() {
        return new Order[0];
    }

    @Override
    public Order[] list(String username) {
        return orderDao.list(username);
    }
}
