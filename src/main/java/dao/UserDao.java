package dao;



import model.User;

import javax.ejb.Local;

/**
 * Created by fish on 3/25/16.
 */
@Local
public interface UserDao {
    public User[] list();

    public Boolean add(String name, String password, String role);
    public Boolean add(String name, String password);

    public User get(String name);

    public Boolean update(String name, User user);

    public Boolean delete(String name);
}
