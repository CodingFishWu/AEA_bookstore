package servlet.home;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by fish on 5/17/16.
 */
@WebServlet("/index/*")
public class HomePage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer out = resp.getWriter();
        String url = req.getRequestURL().toString();
        int i = url.lastIndexOf('/');
        String lo = url.substring(i+1);
//        System.out.println(lo);

        Locale locale = new Locale(lo);
        ResourceBundle bundle = ResourceBundle.getBundle("myResource", locale);

        String welcome = bundle.getString("welcome");
        String bookstore = bundle.getString("bookstore");
        String login = bundle.getString("login");

        // write page
        out.write("<!DOCTYPE html>");
        out.write("<html lang=\"en\">");
        out.write("<head>");
        out.write("<meta charset=\"UTF-8\">");
        out.write("<title>" + bookstore + "</title>");
        out.write("</head>");
        out.write("<body>");
        out.write("<h1>" + welcome + "!!</h1>");
        out.write("<a href=\"/Bookstore/front/index.html\">" + login + "</a>");
        out.write("</body>");
        out.write("</html>");

        resp.setStatus(200);
    }
}
