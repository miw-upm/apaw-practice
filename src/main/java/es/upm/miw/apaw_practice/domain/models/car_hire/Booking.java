package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Booking {

    private LocalDateTime hiredDate;
    private String bookingNumber;
    private BigDecimal totalCost;

    private List<Vehicle> vehiclesList;
    private Renter renter;

    public Booking() {
        //empty for framework
    }

    public Booking(LocalDateTime hiredDate, String bookingNumber, BigDecimal totalCost, List<Vehicle> vehiclesList, Renter renter) {
        this.hiredDate = hiredDate;
        this.bookingNumber = bookingNumber;
        this.totalCost = totalCost;
        this.setVehiclesList(vehiclesList);
        this.setRenter(renter);
    }

    public void addVehicle(Vehicle vehicle) {
        assert vehicle != null;

        this.vehiclesList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        assert vehicle != null;

        this.vehiclesList.remove(vehicle);
    }

    public LocalDateTime getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDateTime hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = vehiclesList;
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
                "hiredDate=" + hiredDate +
                ", bookingNumber=" + bookingNumber +
                ", totalCost=" + totalCost +
                ", vehiclesList=" + vehiclesList +
                ", renter=" + renter +
                '}';
    }
}
