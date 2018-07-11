package notzilly.frozenbooks.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Freezer {
    private String address;
    private int bookQtt;
    private Map<String, Boolean> books = new HashMap<>();

    public Freezer() {}

    public Freezer(String address, int bookQtt, Map<String, Boolean> books) {
        this.address = address;
        this.bookQtt = bookQtt;
        this.books = books;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBookQtt() {
        return bookQtt;
    }

    public void setBookQtt(int bookQtt) {
        this.bookQtt = bookQtt;
    }

    public Map<String, Boolean> getBooks() {
        return books;
    }

    public void setBooks(Map<String, Boolean> books) {
        this.books = books;
    }
}
