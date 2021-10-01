package es.upm.miw.apaw_practice.domain.models.restaurant;

import java.time.LocalDate;

public class Reserve {
    private LocalDate reservationDate;
    private int numPeople;
    private String holder;
    private Table table;

    Reserve(){
        //empty for framework
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "reservationDate=" + reservationDate +
                ", numPeople=" + numPeople +
                ", holder='" + holder + '\'' +
                ", table=" + table +
                '}';
    }
}

