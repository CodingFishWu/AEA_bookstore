package model;

import java.io.Serializable;

/**
 * Created by fish on 4/9/16.
 */
public class Item implements Serializable {
    String bookId;
    int number;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Item() {

    }

    public Item(String bookId, int number) {

        this.bookId = bookId;
        this.number = number;
    }
}
