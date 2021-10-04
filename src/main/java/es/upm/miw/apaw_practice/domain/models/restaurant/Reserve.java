package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.time.LocalDate;

public class Reserve {
    private LocalDate reservationDate;
    private Integer numPeople;
    private String holder;

    Reserve(){
        //empty for framework
    }

    public Reserve(LocalDate reservationDate, Integer numPeople, String holder) {
        this.reservationDate = reservationDate;
        this.numPeople = numPeople;
        this.holder = holder;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "reservationDate=" + reservationDate +
                ", numPeople=" + numPeople +
                ", holder='" + holder + '\'' +
                '}';
    }
}

