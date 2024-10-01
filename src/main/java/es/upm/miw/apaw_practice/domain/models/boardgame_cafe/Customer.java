package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;

public class Customer {
    private String name;
    private LocalDate birthDate;
    private boolean isMember;

    public Customer() {
        // Default constructor
    }

    public Customer(String name, LocalDate birthDate, boolean isMember) {
        this.name = name;
        this.birthDate = birthDate;
        this.isMember = isMember;
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
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isMember=" + isMember +
                '}';
    }
}