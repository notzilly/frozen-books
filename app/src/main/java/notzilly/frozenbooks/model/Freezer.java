package notzilly.frozenbooks.model;

import java.util.List;

public class Freezer {
    private String id;
    private String address;
    private int bookQtt;
    private List<Book> books;


    public Freezer(String id, String address, int bookQtt, List<Book> books) {
        this.id = id;
        this.address = address;
        this.bookQtt = bookQtt;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
