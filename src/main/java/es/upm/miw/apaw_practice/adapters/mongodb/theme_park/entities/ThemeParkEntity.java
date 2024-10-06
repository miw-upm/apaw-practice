package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Document
public class ThemeParkEntity {
    @Id
    private String id;
    private LocalDateTime creationDate;
    private Boolean opened;
    private BigDecimal price;
    private List<RideEntity> rideEntities;

    public ThemeParkEntity() {
        //empty from framework
    }

    public ThemeParkEntity(Boolean opened, BigDecimal price, List<RideEntity> rideEntities) {
        this.id = UUID.randomUUID().toString();
        this.opened = opened;
        this.price = price;
        this.creationDate = LocalDateTime.now();
        this.rideEntities = rideEntities;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<RideEntity> getRideEntities() {
        return rideEntities;
    }

    public void setRideEntities(List<RideEntity> rideEntities) {
        this.rideEntities = rideEntities;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ThemeParkEntity{" +
                "id='" + id + '\'' +
                ", opened=" + opened +
                ", price=" + price +
                ", rideEntities='" + rideEntities + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
