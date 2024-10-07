package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private String email;
    private String name;
    private LocalDate birthDate;
    private boolean isMember;

    public Customer() {
        //empty for framework
    }

    public Customer(String email, String name, LocalDate birthDate, boolean isMember) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.isMember = isMember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isMember == customer.isMember &&
                Objects.equals(email, customer.email) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(birthDate, customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, birthDate, isMember);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isMember=" + isMember +
                '}';
    }
}