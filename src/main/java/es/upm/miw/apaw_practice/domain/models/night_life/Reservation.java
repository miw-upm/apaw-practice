package es.upm.miw.apaw_practice.domain.models.night_life;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private LocalDate date;
    private BigDecimal price;
    private Integer numberOfPeople;
    private List<Customer> customers;

    public Reservation() {
        //empty for framework
    }

    public Reservation(LocalDate date, BigDecimal price, int numberOfPeople, List<Customer> customers) {
        this.date = date;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
        this.customers = customers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", price=" + price +
                ", numberOfPeople=" + numberOfPeople +
                ", customers=" + customers +
                '}';
    }
}
