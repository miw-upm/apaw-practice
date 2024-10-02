package es.upm.miw.apaw_practice.domain.models.night_life;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    public LocalDate date;
    public BigDecimal price;
    public int numberOfPeople;

    public Reservation(){
        //empty for framework
    }

    public Reservation(LocalDate date, BigDecimal price, int numberOfPeople) {
        this.date = date;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", price=" + price +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}
