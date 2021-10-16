package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.math.BigDecimal;

public class Vehicle {

    private String id;
    private String vinNumber;
    private BigDecimal dailyCost;
    private Integer kilometersAmount;
    private Boolean goodCondition;

    public Vehicle() {
        //empty for framework
    }

    public Vehicle(String vinNumber, BigDecimal dailyCost, Integer kilometersAmount, Boolean goodCondition) {
        this.vinNumber = vinNumber;
        this.dailyCost = dailyCost;
        this.kilometersAmount = kilometersAmount;
        this.goodCondition = goodCondition;
    }

    public static Vehicle ofIdVinNumber(Vehicle vehicle) {
        Vehicle vehicleRequested = new Vehicle();
        vehicleRequested.setId(vehicle.getId());
        vehicleRequested.setVinNumber(vehicle.getVinNumber());
        return vehicleRequested;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public Integer getKilometersAmount() {
        return kilometersAmount;
    }

    public void setKilometersAmount(Integer kilometersAmount) {
        this.kilometersAmount = kilometersAmount;
    }

    public Boolean getGoodCondition() {
        return goodCondition;
    }

    public void setGoodCondition(Boolean goodCondition) {
        this.goodCondition = goodCondition;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "VIN_Number='" + vinNumber + '\'' +
                ", dailyCost=" + dailyCost +
                ", kilometersAmount=" + kilometersAmount +
                ", goodCondition=" + goodCondition +
                '}';
    }
}
