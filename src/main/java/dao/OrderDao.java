package dao;

import model.Order;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by fish on 4/8/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local
public interface OrderDao {
    Boolean add(Order order);
    Order[] list(String username);
}
