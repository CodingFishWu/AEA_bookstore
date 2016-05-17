package aspect;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by fish on 5/18/16.
 */
public aspect logging {
    private PrintStream logStream = System.err;
    pointcut register():
            execution(* servlet.UserServlet.doPost());

    before(): register() {
        logStream.println("get into 'user login or register'");
    }

    after(): register() {
        logStream.println("get out of 'user login or register'");
    }
}
