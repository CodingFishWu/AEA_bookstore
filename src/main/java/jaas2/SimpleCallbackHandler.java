package jaas2;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Created by fish on 3/22/16.
 */
public class SimpleCallbackHandler implements CallbackHandler {
    private String username;
    private char[] password;


    public SimpleCallbackHandler(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public SimpleCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password.toCharArray();
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(username);
            }
            else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword((password));
            }
        }
    }
}
