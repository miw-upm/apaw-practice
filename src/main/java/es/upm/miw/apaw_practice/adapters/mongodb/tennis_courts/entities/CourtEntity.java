package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document
public class CourtEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer number;
    private BigDecimal price;
    private Boolean occupied;
    @DBRef
    private List<ReservationEntity> reservations;

    public CourtEntity(){
        //empty from framework
    }

    public CourtEntity(Integer number, BigDecimal price, Boolean occupied, List<ReservationEntity> reservations){
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.price = price;
        this.occupied = occupied;
        this.reservations = reservations;
    }

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
