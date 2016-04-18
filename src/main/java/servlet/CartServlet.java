package servlet;

import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import service.CartService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by fish on 4/5/16.
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @EJB
    CartService cartService;
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        JSONObject jsonObject;
        if (req.getSession().getAttribute("name") == null) {
            resp.setStatus(403);
            return;
        }

        if (action == null) {
            return;
        }

        try {
            if (action.equals("addBook")) {
                String bookId = req.getParameter("bookId");

                if (cartService.put(bookId)) {
                    resp.setStatus(200);
                }
                else {
                    resp.setStatus(400);
                }
            }
            if (action.equals("deleteBook")) {
                String bookId = req.getParameter("bookId");
                if (cartService.delete(bookId)) {
                    resp.setStatus(200);
                }
                else {
                    resp.setStatus(400);
                }
            }
            if (action.equals("toOrder")) {
                String name = (String)req.getSession().getAttribute("name");
                if (cartService.toOrder(name)) {
                    resp.setStatus(201);
                }
                else {
                    resp.setStatus(400);
                }
            }
        }
        catch (Exception e) {
            resp.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getSession().getAttribute("name") == null) {
                resp.setStatus(403);
                return;
            }

            Writer out = resp.getWriter();
            ArrayList<Item> cart = cartService.get();
            JSONArray jsonArray = Tools.itemsToJsonArray(cart);
            out.write(jsonArray.toString());
            resp.setStatus(200);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
