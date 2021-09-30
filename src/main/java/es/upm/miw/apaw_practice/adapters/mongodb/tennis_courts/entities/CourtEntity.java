package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CourtEntity {

    private String id;
    private Integer number;
    private BigDecimal price;
    private Boolean occupied;
    private List<ReservationEntity> reservations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "CourtEntity{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", occupied=" + occupied +
                ", reservations=" + reservations +
                '}';
    }
}
