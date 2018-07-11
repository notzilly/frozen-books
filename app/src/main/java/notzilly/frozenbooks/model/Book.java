package notzilly.frozenbooks.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Book {
    private String description;
    private String publisher;
    private String isbn;
    private int pageNumber;
    private String subtitle;
    private String title;
    private List<String> authors;

    public Book() {}

    public Book(String description, String publisher, String isbn, int pageNumber, String subtitle, String title, List<String> authors) {
        this.description = description;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pageNumber = pageNumber;
        this.subtitle = subtitle;
        this.title = title;
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
