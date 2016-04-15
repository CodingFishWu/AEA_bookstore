package serviceImpl;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import jaas2.SimpleCallbackHandler;
import model.User;
import service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by fish on 3/25/16.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserServiceImpl implements UserService{
    @EJB
    UserDao userDao;

    @Override
    public boolean regist(String name, String password) {
        return userDao.add(name, password, "user");
    }

    @Override
    public boolean changePassword(String name, String password) {
        User user = new User();
        user.setPassword(password);
        return userDao.update(name, user);
    }

    @Override
    public User getByName(String name) {
        return userDao.get(name);
    }

    @Override
    public User[] getList() {
        return userDao.list();
    }
}
