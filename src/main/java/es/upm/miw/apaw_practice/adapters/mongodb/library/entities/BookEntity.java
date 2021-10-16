package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import es.upm.miw.apaw_practice.domain.models.library.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document
public class BookEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String isbn;
    private String title;
    private Boolean available;
    private Integer numbersOfPages;
    private LocalDate publicationDate;
    @DBRef
    private CategoryEntity category;
    @DBRef
    private List<AuthorEntity> authors;

    public BookEntity() {
        // empty for framework
    }

    public static BookEntityBuilders.Id builder() {
        return new BookEntity.Builder();
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

    public Boolean getAvailable() {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", numbersOfPages=" + numbersOfPages +
                ", publicationDate=" + publicationDate +
                ", category=" + category +
                ", authors=" + authors +
                '}';
    }

    public Book toBook() {
        Book book = new Book();
        BeanUtils.copyProperties(this, book);
        return book;
    }

    public static class Builder implements BookEntityBuilders.Id, BookEntityBuilders.Isbn, BookEntityBuilders.Title, BookEntityBuilders.Available, BookEntityBuilders.NumbersOfPages, BookEntityBuilders.PublicationDate, BookEntityBuilders.CategoryEntity, BookEntityBuilders.Optionals {
        private final BookEntity bookEntity;

        public Builder() {
            this.bookEntity = new BookEntity();
        }

        @Override
        public BookEntityBuilders.Isbn id(String id) {
            this.bookEntity.id = id;
            return this;
        }

        @Override
        public BookEntityBuilders.Title isbn(String isbn) {
            this.bookEntity.isbn = isbn;
            return this;
        }

        @Override
        public BookEntityBuilders.Available title(String title) {
            this.bookEntity.title = title;
            return this;
        }

        @Override
        public BookEntityBuilders.NumbersOfPages available(Boolean available) {
            this.bookEntity.available = available;
            return this;
        }

        @Override
        public BookEntityBuilders.PublicationDate numbersOfPages(Integer numbersOfPages) {
            this.bookEntity.numbersOfPages = numbersOfPages;
            return this;
        }

        @Override
        public BookEntityBuilders.CategoryEntity publicationDate(LocalDate publicationDate) {
            this.bookEntity.publicationDate = publicationDate;
            return this;
        }

        @Override
        public BookEntityBuilders.Optionals category(CategoryEntity category) {
            this.bookEntity.category = category;
            return this;
        }

        @Override
        public BookEntityBuilders.Optionals authors(AuthorEntity author) {
            this.bookEntity.authors.add(author);
            return this;
        }

        @Override
        public BookEntity build() {
            return this.bookEntity;
        }
    }
}
