package service;

import model.User;

import javax.ejb.Remote;

/**
 * Created by fish on 3/25/16.
 */
@Remote
public interface UserService {

    // register
    boolean regist(String name, String password);
    // change password
    boolean changePassword(String name, String password);
    // get user by name
    User getByName(String name);

    User[] getList();
}
