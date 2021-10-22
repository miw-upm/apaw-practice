package es.upm.miw.apaw_practice.domain.models.library;

import java.util.List;

public class Reader {
    private String id;
    private String nick;
    private Gender gender;
    private String email;
    private List<Book> books;

    public Reader() {
        // empty for framework
    }

    public Reader(String nick, Gender gender, String email) {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}

