package servlet;

import model.Book;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import service.BookService;
import service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by fish on 4/7/16.
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
    @EJB
    BookService bookService;
    @EJB
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        String action = req.getParameter("action");
        Writer out = resp.getWriter();

        try {
            if (target.equals("user")) {
                if (action.equals("list")) {
                    User[] users = userService.getList();
                    JSONArray jsonArray = Tools.userArrayToJSONArray(users);
                    out.write(jsonArray.toString());
                    resp.setStatus(200);
                }
            }
            if (target.equals("book")) {
                if (action.equals("list")) {
                    Book[] books = bookService.getList();
                    JSONArray jsonArray = Tools.bookArrayToJSONArray(books);
                    out.write(jsonArray.toString());
                    resp.setStatus(200);
                }
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        String action = req.getParameter("action");
        try {
            JSONObject body = new JSONObject(req.getReader().readLine());
            if (target.equals("book")) {
                if (action.equals("add")) {
                    Book book = Tools.jsonObjectToBook(body);
                    if (bookService.add(book)) {
                        resp.setStatus(201);
                    }
                }
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        String action = req.getParameter("action");
        try {
            JSONObject body = new JSONObject(req.getReader().readLine());
            if (target.equals("book")) {
                Book book = Tools.jsonObjectToBook(body);
                String id = req.getParameter("id");
                if (action.equals("update")) {
                    if (bookService.update(id, book)) {
                        resp.setStatus(200);
                    }
                }
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("target");
        try {
            if (target.equals("book")) {
                String id = req.getParameter("id");
                if (bookService.delete(id)) {
                    resp.setStatus(200);
                }
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }
}
