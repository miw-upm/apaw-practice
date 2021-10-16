package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Integer numberDays;
    @DBRef
    private List<VehicleEntity> vehicleEntities;
    @DBRef
    private RenterEntity renterEntity;

    public BookingEntity() {
        //empty for framework
    }

    public BookingEntity(List<VehicleEntity> vehicleEntities, RenterEntity renterEntity, String bookingNumber, Integer numberDays) {
        this.id = UUID.randomUUID().toString();
        this.bookingNumber = bookingNumber;
        this.hiredDate = LocalDateTime.now();
        this.numberDays = numberDays;
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

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public LocalDateTime getHiredDate() {
        return hiredDate;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(Integer numberDays) {
        this.numberDays = numberDays;
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

    public Booking toBooking() {
        Booking booking = new Booking();
        BeanUtils.copyProperties(this, booking, "id", "vehicleEntities", "renterEntity");
        this.vehicleEntities.forEach(vehicleEntity -> booking.addVehicle(vehicleEntity.toVehicle()));
        booking.setRenter(this.renterEntity.toRenter());
        return booking;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", hiredDate=" + hiredDate +
                ", vehicleEntities=" + vehicleEntities +
                ", renterEntity=" + renterEntity +
                '}';
    }
}
