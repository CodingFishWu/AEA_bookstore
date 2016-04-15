package servlet;

import model.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import service.BookService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by fish on 4/5/16.
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
    @EJB
    BookService bookService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        JSONArray jsonArray;
        JSONObject jsonObject;

        if (action == null) {
            return;
        }

        try {
            Writer out = resp.getWriter();
            if (action.equals("info")) {
                Book book = bookService.get(req.getParameter("id"));
                jsonObject = Tools.bookToJSONObject(book);
                out.write(jsonObject.toString());
                resp.setStatus(200);
            }
            if (action.equals("list")) {
                Book[] bookArray = bookService.getList();
                jsonArray = Tools.bookArrayToJSONArray(bookArray);
                out.write(jsonArray.toString());
                resp.setStatus(200);
            }
            if (action.equals("query")) {
                String name = req.getParameter("name");
                String author = req.getParameter("author");
                Book[] bookArray = bookService.getList(name, author);
                jsonArray = Tools.bookArrayToJSONArray(bookArray);
                out.write(jsonArray.toString());
                resp.setStatus(200);
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }
}
