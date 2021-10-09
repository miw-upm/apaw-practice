package es.upm.miw.apaw_practice.domain.models.library;

import java.util.Date;
import java.util.List;

public class Book {

    private Long id;
    private String title;
    private String isbn;
    private Date publication;
    private List<Author> authors;
    private Category category;

    public Book(Long id, String title, String isbn, Date publication, List<Author> authors, Category category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publication = publication;
        this.authors = authors;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
