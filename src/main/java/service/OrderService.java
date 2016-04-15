package service;

import model.Order;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;

/**
 * Created by fish on 4/5/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local
public interface OrderService {
    Order[] list();
    Order[] list(String username);
}
