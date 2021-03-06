package servlet;

import org.json.JSONObject;
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
 * Created by fish on 4/5/16.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @EJB
    UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            return;
        }
        try {
            JSONObject json = new JSONObject(req.getReader().readLine());

            String name = json.getString("name");
            String password = json.getString("password");
            if (name == null || password == null) {
                resp.setStatus(400);
                return;
            }

            if (action.equals("login")) {
                req.login(name, password);
                req.getSession().setAttribute("name", name);
                resp.setStatus(200);
            }
            if (action.equals("register")) {
                if (userService.regist(name, password)) {
//                    System.out.println(name);
                    req.getSession().setAttribute("name", name);
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
        Writer out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        String name = (String)req.getSession().getAttribute("name");
        if (name == null) {
            resp.setStatus(403);
        }
        else {
            jsonObject.put("name", req.getSession().getAttribute("name"));
            out.write(jsonObject.toString());
            resp.setStatus(200);
        }
    }
}
