package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;

public class Customer {
    private int customerId;
    private String name;
    private LocalDate birthDate;
    private boolean isMember;

    public Customer() {
        //empty for framework
    }

    public Customer(int customerId, String name, LocalDate birthDate, boolean isMember) {
        this.customerId = customerId;
        this.name = name;
        this.birthDate = birthDate;
        this.isMember = isMember;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isMember=" + isMember +
                '}';
    }
}