package es.upm.miw.apaw_practice.adapters.mongodb.library.entities;

import es.upm.miw.apaw_practice.domain.models.library.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class ReaderEntity {
    @Id
    private String id;
    private String nick;
    private Gender gender;
    private String email;
    @DBRef
    private List<BookEntity> books;

    public ReaderEntity() {
        // empty for framework
    }

    public ReaderEntity(String nick, Gender gender, String email) {
        this.id = UUID.randomUUID().toString();
        this.nick = nick;
        this.gender = gender;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderEntity that = (ReaderEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nick, that.nick) && gender == that.gender && Objects.equals(email, that.email) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nick, gender, email, books);
    }

    @Override
    public String toString() {
        return "ReaderEntity{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", books=" + books +
                '}';
    }
}
