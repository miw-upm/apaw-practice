package es.upm.miw.apaw_practice.domain.models.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private Boolean available;
    private Integer numbersOfPages;
    private LocalDate publicationDate;
    private Category category;
    private List<Author> authors;

    public Book() {
        // empty for framework
    }

    public static BookBuilders.Isbn builder(){
        return new Builder();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getNumbersOfPages() {
        return numbersOfPages;
    }

    public void setNumbersOfPages(Integer numbersOfPages) {
        this.numbersOfPages = numbersOfPages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public static class Builder implements
            BookBuilders.Isbn, BookBuilders.Title, BookBuilders.Available,
            BookBuilders.NumbersOfPages, BookBuilders.PublicationDate, BookBuilders.ICategory,
            BookBuilders.IAuthor, BookBuilders.Optionals {

        private final Book book;

        public Builder(){
            this.book = new Book();
        }

        @Override
        public BookBuilders.Title isbn(String isbn) {
            this.book.isbn = isbn;
            return this;
        }

        @Override
        public BookBuilders.Available title(String title) {
            this.book.title = title;
            return this;
        }

        @Override
        public BookBuilders.NumbersOfPages available(Boolean available) {
            this.book.available = available;
            return this;
        }

        @Override
        public BookBuilders.PublicationDate numbersOfPages(Integer numbersOfPages) {
            this.book.numbersOfPages = numbersOfPages;
            return this;
        }

        @Override
        public BookBuilders.ICategory publicationDate(LocalDate publicationDate) {
            this.book.publicationDate = publicationDate;
            return this;
        }

        @Override
        public BookBuilders.IAuthor category(Category category) {
            this.book.category = category;
            return this;
        }

        @Override
        public BookBuilders.Optionals authors(Author author) {
            if(this.book.authors == null){
                this.book.authors = new ArrayList<>();
            }
            this.book.authors.add(author);
            return this;
        }

        @Override
        public Book build() {
            return this.book;
        }
    }
}
