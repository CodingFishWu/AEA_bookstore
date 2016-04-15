package servlet;

import model.Order;
import org.json.JSONArray;
import service.OrderService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by fish on 4/9/16.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @EJB
    OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Writer out = resp.getWriter();
        try {
            if (action.equals("list")) {
                String name = (String)req.getSession().getAttribute("name");
                Order[] orders = orderService.list(name);
                JSONArray jsonArray = Tools.orderArrayToJSONArray(orders);
                out.write(jsonArray.toString());
                resp.setStatus(200);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
        }
    }
}
