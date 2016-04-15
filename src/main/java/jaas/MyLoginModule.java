package jaas;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import model.User;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by fish on 3/26/16.
 */
public class MyLoginModule extends UsernamePasswordLoginModule {
    static UserDao userDao = new UserDaoImpl();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        super.initialize(subject, callbackHandler, sharedState, options);
    }

    @Override
    protected String getUsersPassword() throws LoginException {
        String name = super.getUsername();
        return userDao.get(name).getPassword();
    }

    @Override
    protected Group[] getRoleSets() throws LoginException {
        String name = super.getUsername();

        Group[] groups = {new SimpleGroup("Roles")};
        groups[0].addMember(new SimplePrincipal(userDao.get(name).getRole()));
        return groups;
    }
}
