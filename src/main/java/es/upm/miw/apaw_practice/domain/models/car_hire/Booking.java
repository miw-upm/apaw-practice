package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {

    private String bookingNumber;
    private LocalDateTime hiredDate;
    private Integer numberDays;

    private List<Vehicle> vehicleList;
    private Renter renter;

    public Booking() {
        //empty for framework
    }

    public Booking(String bookingNumber, LocalDateTime hiredDate, Integer numberDays, List<Vehicle> vehicleList, Renter renter) {
        this.bookingNumber = bookingNumber;
        this.hiredDate = hiredDate;
        this.numberDays = numberDays;
        this.setVehicleList(vehicleList);
        this.setRenter(renter);
    }

    public void addVehicle(Vehicle vehicle) {
        assert vehicle != null;

        this.vehicleList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        assert vehicle != null;

        this.vehicleList.remove(vehicle);
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

    public void setHiredDate(LocalDateTime hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(Integer numberDays) {
        this.numberDays = numberDays;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", hiredDate=" + hiredDate +
                ", numberDays=" + numberDays +
                ", vehicleList=" + vehicleList +
                ", renter=" + renter +
                '}';
    }
}
