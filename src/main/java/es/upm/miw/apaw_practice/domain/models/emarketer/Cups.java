package es.upm.miw.apaw_practice.domain.models.emarketer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cups {

    private String id;
    private BigDecimal energy;
    private LocalDateTime registrationDate;
    private Customer customer;

    public Cups() {
        //Empty for framework
    }

    public Cups(String id, BigDecimal energy, LocalDateTime registrationDate, Customer customer) {
        this.id = id;
        this.energy = energy;
        this.registrationDate = registrationDate;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getEnergy() {
        return energy;
    }

    public void setEnergy(BigDecimal energy) {
        this.energy = energy;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cups{" +
                "id=" + id +
                ", energy=" + energy +
                ", registrationDate=" + registrationDate +
                '}';
    }

}
