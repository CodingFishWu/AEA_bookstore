package model;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by fish on 4/5/16.
 */
public class Order implements Serializable {
    private String id;
    private String username;
    private Date time;
    private String status;
    private ArrayList<Item> items;

    public Order() {
    }

    public Order(String id, String username, Date time, String status, ArrayList<Item> items) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.status = status;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
