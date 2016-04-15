package servlet;

import model.Book;
import model.Item;
import model.Order;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by fish on 4/8/16.
 */
public class Tools {
    public static JSONObject bookToJSONObject(Book book) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getId());
        jsonObject.put("name", book.getName());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("price", book.getPrice());
        jsonObject.put("stock", book.getStock());
        jsonObject.put("category", book.getCategory());
        return jsonObject;
    }

    public static JSONArray bookArrayToJSONArray(Book[] bookArray) {
        JSONArray jsonArray = new JSONArray();
        for (Book book : bookArray) {
            jsonArray.put(bookToJSONObject(book));
        }
        return jsonArray;
    }

    public static Book jsonObjectToBook(JSONObject jsonObject) {
        Book book = new Book();
        book.setId(jsonObject.getString("id"));
        book.setName(jsonObject.getString("name"));
        book.setAuthor(jsonObject.getString("author"));
        book.setPrice(jsonObject.getDouble("price"));
        book.setStock(jsonObject.getInt("stock"));
        book.setCategory(jsonObject.getString("category"));
        return book;
    }

    public static User jsonObjectToUser(JSONObject jsonObject) {
        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setRole(jsonObject.getString("role"));
        user.setPassword(jsonObject.getString("password"));
        return user;
    }

    public static JSONObject userToJSONObject(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("role", user.getRole());
        return jsonObject;
    }

    public static JSONArray userArrayToJSONArray(User[] userArray) {
        JSONArray jsonArray = new JSONArray();
        for (User user : userArray) {
            jsonArray.put(userToJSONObject(user));
        }
        return jsonArray;
    }

    public static JSONArray itemsToJsonArray(ArrayList<Item> items) {
        JSONArray jsonArray = new JSONArray();
        for (Item item : items) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId", item.getBookId());
            jsonObject.put("number", item.getNumber());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public static JSONObject orderToJSONObject(Order order) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", order.getUsername());
        jsonObject.put("id", order.getId());
        jsonObject.put("time", order.getTime().getTime());
        jsonObject.put("status", order.getStatus());
        jsonObject.put("items", itemsToJsonArray(order.getItems()));
        return jsonObject;
    }

    public static JSONArray orderArrayToJSONArray(Order[] orders) {
        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            jsonArray.put(orderToJSONObject(order));
        }
        return jsonArray;
    }
}
