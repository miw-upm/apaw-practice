package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document
public class BookingEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String bookingNumber;
    private LocalDateTime hiredDate;
    private BigDecimal totalCost;

    private List<VehicleEntity> vehicleEntities;
    private RenterEntity renterEntity;

    public BookingEntity() {
        //empty for framework
    }

    public BookingEntity(List<VehicleEntity> vehicleEntities, RenterEntity renterEntity) {
        this.id = UUID.randomUUID().toString();
        this.bookingNumber = UUID.randomUUID().toString();
        this.hiredDate = LocalDateTime.now();
        this.vehicleEntities = vehicleEntities;
        this.renterEntity = renterEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public LocalDateTime getHiredDate() {
        return hiredDate;
    }

    public List<VehicleEntity> getVehicleEntities() {
        return vehicleEntities;
    }

    public void setVehicleEntities(List<VehicleEntity> vehicleEntities) {
        this.vehicleEntities = vehicleEntities;
    }

    public RenterEntity getRenterEntity() {
        return renterEntity;
    }

    public void setRenterEntity(RenterEntity renterEntity) {
        this.renterEntity = renterEntity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", hiredDate=" + hiredDate +
                ", totalCost=" + totalCost +
                ", vehicleEntities=" + vehicleEntities +
                ", renterEntity=" + renterEntity +
                '}';
    }
}
