package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities;

import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class ReserveEntity {
    private LocalDate reservationDate;
    private Integer numPeople;
    private String holder;

    public ReserveEntity(){
        //empty for framework
    }

    public ReserveEntity(Reserve reserve){
        BeanUtils.copyProperties(reserve,this);
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
        return "ReserveEntity{" +
                "reservationDate=" + reservationDate +
                ", numPeople=" + numPeople +
                ", holder='" + holder + '\'' +
                '}';
    }
}
