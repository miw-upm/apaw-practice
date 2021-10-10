package es.upm.miw.apaw_practice.domain.models.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reader {
    private Long id;
    private String name;
    private String dni;
    private Date birthDate;
    private String email;
    private List<Book> books;

    public Reader(Long id, String name, String dni, Date birthDate, String email) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
